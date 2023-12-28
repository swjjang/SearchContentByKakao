package com.swjjang7.searchcontentbykakao.data.model

import com.google.gson.annotations.SerializedName

data class BaseListDto<T>(
    @SerializedName(value = "meta") val meta: MetaDto,
    @SerializedName(value = "documents") val documents: List<T>
)
