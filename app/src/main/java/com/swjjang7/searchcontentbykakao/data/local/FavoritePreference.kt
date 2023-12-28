package com.swjjang7.searchcontentbykakao.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import javax.inject.Inject

class FavoritePreferences @Inject constructor(
    private val preference: SharedPreferences,
    private val gson: Gson,
) {
    companion object {
        private const val KEY_FAVORITE_CONTENTS = "favorite_contents"
    }

    fun getList(): List<FavoriteContent> {
        val list = preference.getString(KEY_FAVORITE_CONTENTS, null) ?: return listOf()
        return list.toListByJson()
    }

    fun add(favoriteContent: FavoriteContent) {
        val list = getList().toMutableList()
        if (list.contains(favoriteContent)) {
            // 이미 저장된 상황 인데... skip 하지 않고, 기존에 삭제가 되지 않았다 판단하고 지워줌
            list.remove(favoriteContent)
        }

        list.add(favoriteContent)

        setValue(list.toStringByJson())
    }

    fun remove(favoriteContent: FavoriteContent) {
        val list = getList().toMutableList()
        list.remove(favoriteContent)

        setValue(list.toStringByJson())
    }

    private fun setValue(text: String?) {
        with(preference.edit()) {
            putString(KEY_FAVORITE_CONTENTS, text)
            apply()
        }
    }

    private fun String.toListByJson(): List<FavoriteContent> = if (isNotEmpty()) {
        try {
            gson.fromJson(
                this,
                TypeToken.getParameterized(ArrayList::class.java, FavoriteContent::class.java).type
            ) ?: listOf()
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    } else {
        listOf()
    }

    private fun List<FavoriteContent>.toStringByJson(): String = if (isNotEmpty()) {
        try {
            gson.toJson(this) ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    } else {
        ""
    }
}