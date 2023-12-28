package com.swjjang7.searchcontentbykakao.domain.usecase

import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import com.swjjang7.searchcontentbykakao.domain.model.SearchResult
import com.swjjang7.searchcontentbykakao.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchContentsUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    operator fun invoke(searchText: String, page: Int, size: Int, requestImage: Boolean, requestVideo: Boolean): Flow<ApiResult<SearchResult>> {
        return searchRepository.getContents(searchText, page, size, requestImage, requestVideo)
    }
}