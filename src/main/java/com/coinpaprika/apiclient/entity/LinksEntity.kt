package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class LinksEntity(
    val explorer: List<String>?,
    val facebook: List<String>?,
    val reddit: List<String>?,
    @SerializedName("source_code") val sourceCode: List<String>?,
    val website: List<String>?,
    val youtube: List<String>?
)