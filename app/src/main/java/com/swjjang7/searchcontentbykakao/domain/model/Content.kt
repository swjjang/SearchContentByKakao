package com.swjjang7.searchcontentbykakao.domain.model

data class Content(
    val type: ContentType,
    val imageUrl: String,
    val dateTime: String,
    val isFavorite: Boolean = false,
    val description: String? = null,
)
