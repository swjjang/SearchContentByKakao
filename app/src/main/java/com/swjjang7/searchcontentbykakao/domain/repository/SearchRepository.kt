package com.swjjang7.searchcontentbykakao.domain.repository

import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import com.swjjang7.searchcontentbykakao.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchList(searchQuery: String, page: Int, size: Int): Flow<ApiResult<SearchResult>>
}