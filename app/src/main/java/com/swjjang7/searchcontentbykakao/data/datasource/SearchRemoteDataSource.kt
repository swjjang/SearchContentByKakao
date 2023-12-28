package com.swjjang7.searchcontentbykakao.data.datasource

import com.swjjang7.searchcontentbykakao.data.model.BaseListDto
import com.swjjang7.searchcontentbykakao.data.model.ImageItemDto
import com.swjjang7.searchcontentbykakao.data.model.VideoItemDto
import com.swjjang7.searchcontentbykakao.data.network.NetworkService
import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    suspend fun getVideos(
        query: String,
        page: Int,
        size: Int = NetworkService.DEFAULT_PAGE_SIZE,
    ): Flow<ApiResult<BaseListDto<VideoItemDto>>>

    suspend fun getImages(
        query: String,
        page: Int,
        size: Int = NetworkService.DEFAULT_PAGE_SIZE,
    ): Flow<ApiResult<BaseListDto<ImageItemDto>>>
}