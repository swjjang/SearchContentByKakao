package com.swjjang7.searchcontentbykakao.domain.repository

import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent

interface FavoriteRepository {
    suspend fun getList(): List<FavoriteContent>
    suspend fun add(favoriteContent: FavoriteContent)
    suspend fun remove(favoriteContent: FavoriteContent)
}