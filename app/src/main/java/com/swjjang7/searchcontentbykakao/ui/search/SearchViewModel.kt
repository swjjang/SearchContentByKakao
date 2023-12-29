package com.swjjang7.searchcontentbykakao.ui.search

import androidx.lifecycle.viewModelScope
import com.swjjang7.searchcontentbykakao.domain.model.Content
import com.swjjang7.searchcontentbykakao.domain.model.ContentType
import com.swjjang7.searchcontentbykakao.domain.model.asFavoriteContent
import com.swjjang7.searchcontentbykakao.domain.model.onError
import com.swjjang7.searchcontentbykakao.domain.model.onSuccess
import com.swjjang7.searchcontentbykakao.domain.usecase.AddFavoriteContentsUseCase
import com.swjjang7.searchcontentbykakao.domain.usecase.GetSearchContentsUseCase
import com.swjjang7.searchcontentbykakao.domain.usecase.RemoveFavoriteContentsUseCase
import com.swjjang7.searchcontentbykakao.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchContentsUseCase: GetSearchContentsUseCase,
    private val addFavoriteContentsUseCase: AddFavoriteContentsUseCase,
    private val removeFavoriteContentsUseCase: RemoveFavoriteContentsUseCase,
) : BaseViewModel() {
    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }

    private val _contents = MutableStateFlow<List<Content>>(emptyList())
    val contents: StateFlow<List<Content>> = _contents

    private lateinit var inputTextJob: Job

    private var requestParam: RequestParam = RequestParam()

    private data class RequestParam(
        val inputText: String = "",
        val page: Int = 1,
        val pageSize: Int = DEFAULT_PAGE_SIZE,
        val isImageEnd: Boolean = false,
        val isVideoEnd: Boolean = false,
    )

    private fun fetchContents(param: RequestParam) {
        showLoading(true)

        viewModelScope.launch(exceptionHandler) {
            getSearchContentsUseCase(
                param.inputText,
                param.page,
                param.pageSize,
                param.isImageEnd,
                param.isVideoEnd
            ).collect { result ->
                result.onSuccess {
                    showLoading(false)

                    requestParam =
                        param.copy(isImageEnd = it.isImageEnd, isVideoEnd = it.isVideoEnd)

                    val list = contents.value.addHeaderList(it.contents, param.page)
                    _contents.emit(list)
                }.onError {
                    showLoading(false)

                    requestParam = param.copy(isImageEnd = true, isVideoEnd = true)

                    showToast(it.toString())
                }
            }
        }
    }

    private fun List<Content>.addHeaderList(new: List<Content>, page: Int = 1): List<Content> {
        if (page == 1) return new

        return toMutableList().apply {
            add(Content(ContentType.HEADER, "", "", false, "현재 페이지 : $page"))
            addAll(new)
        }
    }

    fun changeInputKeyword(s: String) {
        if (requestParam.inputText == s) {
            return
        }

        if (::inputTextJob.isInitialized) {
            inputTextJob.cancel()
        }

        inputTextJob = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            delay(500)

            requestParam = RequestParam(inputText = s)
            _contents.emit(emptyList())

            if (s.isNotEmpty()) {
                fetchContents(requestParam)
            }
        }


    }

    fun changeFavoriteContent(content: Content) {
        viewModelScope.launch(exceptionHandler) {
            if (content.isFavorite) {
                removeFavoriteContentsUseCase(content.asFavoriteContent())
            } else {
                addFavoriteContentsUseCase(content.asFavoriteContent())
            }
        }
    }

    fun nextPageLoadable(position: Int): Boolean {
        if (isLoading()) {
            return false
        }

        if (requestParam.isImageEnd && requestParam.isVideoEnd) {
            return false
        }

        return position > contents.value.size - (DEFAULT_PAGE_SIZE / 3 * 2)
    }

    fun fetchNextPage() {
        fetchContents(requestParam.copy(page = requestParam.page + 1))
    }
}