package com.swjjang7.searchcontentbykakao.data.module

import com.swjjang7.searchcontentbykakao.data.repositoryimpl.FavoriteRepositoryImpl
import com.swjjang7.searchcontentbykakao.data.repositoryimpl.SearchRepositoryImpl
import com.swjjang7.searchcontentbykakao.domain.repository.FavoriteRepository
import com.swjjang7.searchcontentbykakao.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository
}