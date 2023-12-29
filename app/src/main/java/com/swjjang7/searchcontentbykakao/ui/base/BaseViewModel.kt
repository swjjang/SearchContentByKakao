package com.swjjang7.searchcontentbykakao.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _toast = MutableSharedFlow<String>()
    val toast: SharedFlow<String> = _toast

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("SearchContentByKakao","exception : $exception")
        when(exception) {
            is CancellationException -> showToast("작업이 취소 되었습니다.")
            else -> showToast(exception.message ?: "$exception")
        }
    }

    fun showLoading(show: Boolean) = viewModelScope.launch { _loading.emit(show) }

    fun isLoading() = loading.value

    fun showToast(message: String) = viewModelScope.launch { _toast.emit(message) }
}