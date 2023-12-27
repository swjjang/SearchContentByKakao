package com.swjjang7.searchcontentbykakao.domain.model

data class SearchResult(
    val isImageEnd: Boolean = true,
    val isVideoEnd: Boolean = true,
    val contents: List<Content> = emptyList()
) {
    operator fun plus(favoriteContentList: List<FavoriteContent>): SearchResult {
        return SearchResult(isImageEnd,
            isVideoEnd,
            contents.map {
                it.copy(isFavorite = favoriteContentList.hasItem(it))
            })
    }

    private fun List<FavoriteContent>.hasItem(content: Content): Boolean = this.find {
        it.imageUrl == content.imageUrl && it.type == content.type && it.dateTime == content.dateTime
    } != null

    fun isAllEnd(): Boolean = isImageEnd && isVideoEnd
}
