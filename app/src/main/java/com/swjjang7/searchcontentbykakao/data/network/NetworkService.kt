package com.swjjang7.searchcontentbykakao.data.network

import com.swjjang7.searchcontentbykakao.data.model.BaseListDto
import com.swjjang7.searchcontentbykakao.data.model.ImageItemDto
import com.swjjang7.searchcontentbykakao.data.model.VideoItemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    companion object {
        const val BASE_URL = "https://newsapi.org/"
        private const val REST_API_KEY = "c86afa1428dcf896a0d6d2d981977bc0"
        const val AUTHORIZATION = "KakaoAK $REST_API_KEY"
        const val DEFAULT_PAGE_SIZE = 10

        enum class Sort(val value: String) {
            ACCURACY("accuracy"), RECENCY("recency")
        }
    }

    @GET(value = "v2/search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int = DEFAULT_PAGE_SIZE,
        @Query("sort") sort: String = Sort.RECENCY.value,
    ): Response<BaseListDto<ImageItemDto>>

    @GET(value = "v2/search/vclip")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int = DEFAULT_PAGE_SIZE,
        @Query("sort") sort: String = Sort.RECENCY.value,
    ): Response<BaseListDto<VideoItemDto>>
}