package com.swjjang7.searchcontentbykakao.data.model

import com.swjjang7.searchcontentbykakao.domain.model.Content
import com.swjjang7.searchcontentbykakao.domain.model.ContentType
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent

fun VideoItemDto.asContent(isFavorite: Boolean = false): Content {
    return Content(ContentType.VIDEO, thumbnailUrl, dateTime, isFavorite)
}

fun ImageItemDto.asContent(isFavorite: Boolean = false): Content {
    return Content(ContentType.IMAGE, imageUrl, dateTime, isFavorite)
}

fun List<FavoriteContent>.hasItem(videoItemDto: VideoItemDto): Boolean = this.find {
    it.imageUrl == videoItemDto.thumbnailUrl && it.type == ContentType.VIDEO && it.dateTime == videoItemDto.dateTime
} != null

fun List<FavoriteContent>.hasItem(imageItemDto: ImageItemDto): Boolean = this.find {
    it.imageUrl == imageItemDto.imageUrl && it.type == ContentType.IMAGE && it.dateTime == imageItemDto.dateTime
} != null