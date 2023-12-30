package com.swjjang7.searchcontentbykakao.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetaDto(
    @SerializedName(value = "total_count") val totalCount: Int,
    @SerializedName(value = "pageable_count") val pageableCount: Int,
    @SerializedName(value = "is_end") val isEnd: Boolean,
) : Parcelable
