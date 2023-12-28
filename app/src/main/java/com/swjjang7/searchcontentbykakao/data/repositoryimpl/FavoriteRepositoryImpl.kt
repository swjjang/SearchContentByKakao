package com.swjjang7.searchcontentbykakao.data.repositoryimpl

import com.swjjang7.searchcontentbykakao.data.datasource.FavoriteLocalDataSource
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource
) : FavoriteRepository {
    override suspend fun getList(): List<FavoriteContent> = favoriteLocalDataSource.getList()

    override suspend fun add(favoriteContent: FavoriteContent) {
        return favoriteLocalDataSource.add(favoriteContent)
    }

    override suspend fun remove(favoriteContent: FavoriteContent) {
        return favoriteLocalDataSource.remove(favoriteContent)
    }
}