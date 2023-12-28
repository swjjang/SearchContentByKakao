package com.swjjang7.searchcontentbykakao.data.module

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.swjjang7.searchcontentbykakao.data.local.FavoritePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName + ".favorite", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideFavoritePreference(
        preference: SharedPreferences,
        gson: Gson,
    ): FavoritePreferences = FavoritePreferences(preference, gson)
}