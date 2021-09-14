package com.coinpaprika.apiclient.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WhitepaperEntity(
    val link: String?,
    val thumbnail: String?
)