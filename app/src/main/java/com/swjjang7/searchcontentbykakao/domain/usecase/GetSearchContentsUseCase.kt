package com.swjjang7.searchcontentbykakao.domain.usecase

import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import com.swjjang7.searchcontentbykakao.domain.model.SearchResult
import com.swjjang7.searchcontentbykakao.domain.model.onError
import com.swjjang7.searchcontentbykakao.domain.model.onSuccess
import com.swjjang7.searchcontentbykakao.domain.repository.FavoriteRepository
import com.swjjang7.searchcontentbykakao.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchContentsUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val favoriteRepository: FavoriteRepository,
) {
    operator fun invoke(searchText: String, page: Int, size: Int): Flow<ApiResult<SearchResult>> {
        return flow {
            searchRepository.getContents(searchText, page, size).collect { apiResult ->
                apiResult.onSuccess {
                    val favoriteContents = favoriteRepository.getList()
                    emit(ApiResult.Success(it + favoriteContents))
                }.onError {
                    // TODO : 확인 필요
                    emit(apiResult)
                }
            }
        }
    }
}