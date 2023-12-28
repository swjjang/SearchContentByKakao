package com.swjjang7.searchcontentbykakao.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*
이름	                타입	            설명
collection	        String	        컬렉션
thumbnail_url	    String	        미리보기 이미지 URL
image_url	        String	        이미지 URL
width	            Integer	        이미지의 가로 길이
height	            Integer	        이미지의 세로 길이
display_sitename	String	        출처
doc_url	            String	        문서 URL
datetime	        Datetime	    문서 작성시간, ISO 8601
                                    [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
 */
@Parcelize
data class ImageItemDto(
    @SerializedName(value = "collection") val collection: String,
    @SerializedName(value = "thumbnail_url") val thumbnailUrl: String,
    @SerializedName(value = "image_url") val imageUrl: String,
    @SerializedName(value = "width") val width: Int,
    @SerializedName(value = "height") val height: Int,
    @SerializedName(value = "display_sitename") val displaySitename: String,
    @SerializedName(value = "doc_url") val docUrl: String,
    @SerializedName(value = "datetime") val dateTime: String,
): Parcelable
