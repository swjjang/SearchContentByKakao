package com.swjjang7.searchcontentbykakao.ui.main

import com.swjjang7.searchcontentbykakao.domain.usecase.GetFavoriteContentsUseCase
import com.swjjang7.searchcontentbykakao.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFavoriteContentsUseCase: GetFavoriteContentsUseCase,
): BaseViewModel() {
}