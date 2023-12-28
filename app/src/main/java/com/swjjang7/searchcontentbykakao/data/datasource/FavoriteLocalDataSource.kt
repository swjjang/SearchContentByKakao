package com.swjjang7.searchcontentbykakao.data.datasource

import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent

interface FavoriteLocalDataSource {
    suspend fun getList(): List<FavoriteContent>
    suspend fun add(favoriteContent: FavoriteContent)
    suspend fun remove(favoriteContent: FavoriteContent)
}