package com.swjjang7.searchcontentbykakao.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor() : ViewModel() {
    private val _share: MutableStateFlow<Any> = MutableStateFlow(Any())
    val share: StateFlow<Any> = _share

    private var _updateFunc: (() -> Unit)? = null

    fun updateNotify(any: Any) {
        viewModelScope.launch {
            _share.emit(any)
        }
    }

    fun invokeUpdateNotify() {
        viewModelScope.launch {
            _updateFunc?.invoke()
        }
    }

    fun setUpdateFunc(func: (() -> Unit)?) {
        _updateFunc = func
    }
}