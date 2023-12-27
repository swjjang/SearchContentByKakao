package com.swjjang7.searchcontentbykakao.domain.model

sealed class ApiResult<T> {
    data object Loading : ApiResult<Nothing>()

    data class Success<T>(val data: T) : ApiResult<T>()

    sealed class Error : ApiResult<Nothing>() {
        data class Http(val code: Int, val message: String?) : Error()
        data class Network(val throwable: Throwable) : Error()
        data class Unknown(val throwable: Throwable) : Error()
    }

    override fun toString(): String {
        return when (this) {
            is Loading -> "[Loading]"
            is Success<T> -> "[Success] $data"
            is Error.Http -> "[Error.Http] $code, $message"
            is Error.Network -> "[Error.Network] $throwable"
            is Error.Unknown -> "[Error.Unknown] $throwable"
        }
    }
}

inline fun <T> ApiResult<T>.onLoading(
    action: (loading: ApiResult.Loading) -> Unit
): ApiResult<T> {
    if (this is ApiResult.Loading) {
        action(this)
    }
    return this
}

inline fun <T> ApiResult<T>.onSuccess(
    action: (value: T) -> Unit
): ApiResult<T> {
    if (this is ApiResult.Success) {
        action(data)
    }
    return this
}

inline fun <T> ApiResult<T>.onError(
    action: (error: ApiResult.Error) -> Unit
): ApiResult<T> {
    if (this is ApiResult.Error) {
        action(this)
    }
    return this
}