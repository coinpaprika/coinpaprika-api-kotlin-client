package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoEntity(
    @Json(name = "author_url") val authorUrl: String,
    @Json(name = "author_name") val authorName: String,
    val height: Int,
    val width: Int,
    val type: String,
    @Json(name = "provider_url") val providerUrl: String,
    @Json(name = "provider_name") val providerName: String,
    val html: String,
    val title: String,
    @Json(name = "thumbnail_url") val thumbnailUrl: String,
    @Json(name = "thumbnail_height") val thumbnailHeight: Int,
    @Json(name = "thumbnail_width") val thumbnailWidth: Int,
    val version: String
)