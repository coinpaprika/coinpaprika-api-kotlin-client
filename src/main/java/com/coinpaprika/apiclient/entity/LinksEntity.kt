package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksEntity(
    val explorer: List<String>?,
    val facebook: List<String>?,
    val reddit: List<String>?,
    @Json(name = "source_code") val sourceCode: List<String>?,
    val website: List<String>?,
    val youtube: List<String>?
)