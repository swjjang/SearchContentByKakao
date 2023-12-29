package com.swjjang7.searchcontentbykakao.ui.favorite

import androidx.lifecycle.viewModelScope
import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.domain.model.onError
import com.swjjang7.searchcontentbykakao.domain.model.onSuccess
import com.swjjang7.searchcontentbykakao.domain.usecase.GetFavoriteContentsUseCase
import com.swjjang7.searchcontentbykakao.domain.usecase.RemoveFavoriteContentsUseCase
import com.swjjang7.searchcontentbykakao.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteContentsUseCase: GetFavoriteContentsUseCase,
    private val removeFavoriteContentsUseCase: RemoveFavoriteContentsUseCase,
) : BaseViewModel() {
    private val _contents = MutableStateFlow<List<FavoriteContent>>(emptyList())
    val contents: StateFlow<List<FavoriteContent>> = _contents

    private fun fetchFavoriteContents() {
        showLoading(true)

        viewModelScope.launch(exceptionHandler) {
            getFavoriteContentsUseCase().catch {
                emit(
                    ApiResult.Error.Unknown(it)
                )
            }.collect { result ->
                result.onSuccess {
                    showLoading(false)
                    _contents.emit(it)
                }.onError {
                    showLoading(false)
                    showToast(it.toString())
                }
            }
        }
    }

    fun removeFavoriteContent(favoriteContent: FavoriteContent) {
        viewModelScope.launch(exceptionHandler) {
            removeFavoriteContentsUseCase(favoriteContent)
        }
    }
}