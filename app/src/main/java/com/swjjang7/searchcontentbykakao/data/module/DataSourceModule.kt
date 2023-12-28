package com.swjjang7.searchcontentbykakao.data.module

import com.swjjang7.searchcontentbykakao.data.datasource.FavoriteLocalDataSource
import com.swjjang7.searchcontentbykakao.data.datasource.FavoriteLocalDataSourceImpl
import com.swjjang7.searchcontentbykakao.data.datasource.SearchRemoteDataSource
import com.swjjang7.searchcontentbykakao.data.datasource.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindFavoriteLocalDataSource(impl: FavoriteLocalDataSourceImpl) : FavoriteLocalDataSource

    @Binds
    fun bindSearchRemoteDataSource(impl: SearchRemoteDataSourceImpl): SearchRemoteDataSource
}