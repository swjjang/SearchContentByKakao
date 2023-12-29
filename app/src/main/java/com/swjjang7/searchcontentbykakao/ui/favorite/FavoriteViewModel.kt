package com.swjjang7.searchcontentbykakao.ui.favorite

import androidx.lifecycle.viewModelScope
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.domain.usecase.RemoveFavoriteContentsUseCase
import com.swjjang7.searchcontentbykakao.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val removeFavoriteContentsUseCase: RemoveFavoriteContentsUseCase,
) : BaseViewModel() {
    private val _contents = MutableStateFlow<List<FavoriteContent>>(emptyList())
    val contents: StateFlow<List<FavoriteContent>> = _contents

    private val _favoriteUpdate = MutableSharedFlow<Boolean>()
    val favoriteUpdate: SharedFlow<Boolean> = _favoriteUpdate

    // 데이터 처리는 MainActivity에서 처리

    fun removeFavoriteContent(favoriteContent: FavoriteContent) {
        viewModelScope.launch(exceptionHandler) {
            removeFavoriteContentsUseCase(favoriteContent)

            _favoriteUpdate.emit(true)
        }
    }

    fun updateFavoriteContents(list: List<FavoriteContent>) {
        viewModelScope.launch(exceptionHandler) {
            _contents.emit(list)
        }
    }
}