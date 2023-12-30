package com.swjjang7.searchcontentbykakao.ui.main

import androidx.lifecycle.viewModelScope
import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.domain.model.onError
import com.swjjang7.searchcontentbykakao.domain.model.onSuccess
import com.swjjang7.searchcontentbykakao.domain.usecase.GetFavoriteContentsUseCase
import com.swjjang7.searchcontentbykakao.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFavoriteContentsUseCase: GetFavoriteContentsUseCase,
) : BaseViewModel() {
    private val _favoriteContents: MutableStateFlow<List<FavoriteContent>> =
        MutableStateFlow(listOf())
    val favoriteContents: StateFlow<List<FavoriteContent>> = _favoriteContents

    fun fetchFavoriteContents() {
        viewModelScope.launch(exceptionHandler) {
            getFavoriteContentsUseCase().catch {
                emit(
                    ApiResult.Error.Unknown(it)
                )
            }.collect { result ->
                result.onSuccess {
                    _favoriteContents.emit(it)
                }.onError {
                    showToast(it.toString())
                }
            }
        }
    }
}