package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class VideoEntity(
    @SerializedName("author_url") val authorUrl: String,
    @SerializedName("author_name") val authorName: String,
    val height: Int,
    val width: Int,
    val type: String,
    @SerializedName("provider_url") val providerUrl: String,
    @SerializedName("provider_name") val providerName: String,
    val html: String,
    val title: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("thumbnail_height") val thumbnailHeight: Int,
    @SerializedName("thumbnail_width") val thumbnailWidth: Int,
    val version: String
)