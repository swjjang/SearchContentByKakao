package com.swjjang7.searchcontentbykakao.data.network

import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

fun <T> apiFlow(func: suspend () -> Response<T>): Flow<ApiResult<T>> = flow {
    try {
        val response = func()
        if (response.isSuccessful) {
            response.body()?.let { data ->
                emit(ApiResult.Success(data))
            } ?: run {
                emit(ApiResult.Error.Unknown(IllegalStateException("Response body was null")))
            }
        } else {
            response.apply {
                emit(ApiResult.Error.Http(code(), message()))
            }
        }
    } catch (e: IOException) {
        emit(ApiResult.Error.Network(e))
    } catch (e: Exception) {
        emit(ApiResult.Error.Unknown(e))
    }
}.catch {
    emit(ApiResult.Error.Unknown(it))
}.flowOn(Dispatchers.IO)