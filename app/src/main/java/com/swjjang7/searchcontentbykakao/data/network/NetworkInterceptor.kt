package com.swjjang7.searchcontentbykakao.data.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization", NetworkService.AUTHORIZATION)
                .build()
            proceed(newRequest)
        }
}