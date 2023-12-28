package com.swjjang7.searchcontentbykakao.data.datasource

import com.swjjang7.searchcontentbykakao.data.local.FavoritePreferences
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import javax.inject.Inject

class FavoriteLocalDataSourceImpl @Inject constructor(
    private val preferences: FavoritePreferences
) : FavoriteLocalDataSource {
    override suspend fun getList(): List<FavoriteContent> = preferences.getList()

    override suspend fun add(favoriteContent: FavoriteContent) = preferences.add(favoriteContent)

    override suspend fun remove(favoriteContent: FavoriteContent) = preferences.remove(favoriteContent)
}