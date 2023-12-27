package com.swjjang7.searchcontentbykakao.domain.model

fun Content.asFavoriteContent(): FavoriteContent {
    return FavoriteContent(imageUrl, dateTime, type)
}