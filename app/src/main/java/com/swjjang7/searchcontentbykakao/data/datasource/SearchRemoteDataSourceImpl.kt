package com.swjjang7.searchcontentbykakao.data.datasource

import com.swjjang7.searchcontentbykakao.data.model.BaseListDto
import com.swjjang7.searchcontentbykakao.data.model.ImageItemDto
import com.swjjang7.searchcontentbykakao.data.model.VideoItemDto
import com.swjjang7.searchcontentbykakao.data.network.NetworkService
import com.swjjang7.searchcontentbykakao.data.network.apiFlow
import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val networkService: NetworkService
) : SearchRemoteDataSource {
    override suspend fun getVideos(
        query: String,
        page: Int,
        size: Int
    ): Flow<ApiResult<BaseListDto<VideoItemDto>>> = apiFlow {
        networkService.getVideos(query, page, size)
    }

    override suspend fun getImages(
        query: String,
        page: Int,
        size: Int
    ): Flow<ApiResult<BaseListDto<ImageItemDto>>> = apiFlow {
        networkService.getImages(query, page, size)
    }
}