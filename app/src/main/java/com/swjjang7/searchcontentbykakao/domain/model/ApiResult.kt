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