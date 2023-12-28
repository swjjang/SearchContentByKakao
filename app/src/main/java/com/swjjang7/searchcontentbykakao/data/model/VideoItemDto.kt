package com.swjjang7.searchcontentbykakao.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*
이름	            타입	            설명
title	        String	        동영상 제목
url	            String	        동영상 링크
datetime	    Datetime	    동영상 등록일, ISO 8601
                                [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
play_time	    Integer	        동영상 재생시간, 초 단위
thumbnail	    String	        동영상 미리보기 URL
author	        String	        동영상 업로더
 */
@Parcelize
data class VideoItemDto(
    @SerializedName(value = "title") val title: String,
    @SerializedName(value = "url") val url: String,
    @SerializedName(value = "datetime") val dateTime: String,
    @SerializedName(value = "play_time") val playTime: String,
    @SerializedName(value = "thumbnail") val thumbnailUrl: String,
    @SerializedName(value = "author") val author: String,
) : Parcelable
