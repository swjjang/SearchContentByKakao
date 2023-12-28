package com.swjjang7.searchcontentbykakao.data.repositoryimpl

import com.swjjang7.searchcontentbykakao.data.datasource.FavoriteLocalDataSource
import com.swjjang7.searchcontentbykakao.data.datasource.SearchRemoteDataSource
import com.swjjang7.searchcontentbykakao.data.model.BaseListDto
import com.swjjang7.searchcontentbykakao.data.model.ImageItemDto
import com.swjjang7.searchcontentbykakao.data.model.MetaDto
import com.swjjang7.searchcontentbykakao.data.model.VideoItemDto
import com.swjjang7.searchcontentbykakao.data.model.asContent
import com.swjjang7.searchcontentbykakao.data.model.hasItem
import com.swjjang7.searchcontentbykakao.data.module.IoDispatcher
import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import com.swjjang7.searchcontentbykakao.domain.model.Content
import com.swjjang7.searchcontentbykakao.domain.model.SearchResult
import com.swjjang7.searchcontentbykakao.domain.model.onError
import com.swjjang7.searchcontentbykakao.domain.model.onSuccess
import com.swjjang7.searchcontentbykakao.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : SearchRepository {
    override fun getContents(
        searchQuery: String,
        page: Int,
        size: Int,
        requestImage: Boolean,
        requestVideo: Boolean
    ): Flow<ApiResult<SearchResult>> {
        return flow {
            val favoriteContents = favoriteLocalDataSource.getList()
            val videoFlow = if (requestVideo) {
                searchRemoteDataSource.getVideos(searchQuery, page, size)
            } else {
                emptyVideoFlow()
            }

            val imageFlow = if (requestImage) {
                searchRemoteDataSource.getImages(searchQuery, page, size)
            } else {
                emptyImageFlow()
            }

            videoFlow.zip(imageFlow) { videoResult, imageResult ->
                var isVideoEnd = false
                var isImageEnd = false
                val contents = mutableListOf<Content>()

                var videoError: ApiResult.Error? = null
                var imageError: ApiResult.Error? = null

                videoResult.onSuccess { baseDto ->
                    isVideoEnd = baseDto.meta.isEnd

                    baseDto.documents.forEach {
                        contents.add(it.asContent(favoriteContents.hasItem(it)))
                    }
                }.onError {
                    // API 에러시 더이상 리스트에서 불러올것이 없다고 판단한다.
                    isVideoEnd = true
                    videoError = it
                }

                imageResult.onSuccess { baseDto ->
                    isImageEnd = baseDto.meta.isEnd

                    baseDto.documents.forEach {
                        contents.add(it.asContent(favoriteContents.hasItem(it)))
                    }
                }.onError {
                    // API 에러시 더이상 리스트에서 불러올것이 없다고 판단한다.
                    isImageEnd = true
                    imageError = it
                }

                val result = when {
                    isImageEnd && videoError != null -> videoError!!
                    isVideoEnd && imageError != null -> imageError!!
                    else -> {
                        ApiResult.Success(
                            SearchResult(
                                isImageEnd,
                                isVideoEnd,
                                contents.sortedByDescending { it.dateTime })
                        )
                    }
                }

                result
            }.flowOn(dispatcher).collect {
                emit(it)
            }
        }
    }

    private fun emptyVideoFlow(): Flow<ApiResult<BaseListDto<VideoItemDto>>> {
        return flowOf(
            ApiResult.Success(
                BaseListDto(
                    MetaDto(0, 0, true),
                    listOf()
                )
            )
        )
    }

    private fun emptyImageFlow(): Flow<ApiResult<BaseListDto<ImageItemDto>>> {
        return flowOf(
            ApiResult.Success(
                BaseListDto(
                    MetaDto(0, 0, true),
                    listOf()
                )
            )
        )
    }
}