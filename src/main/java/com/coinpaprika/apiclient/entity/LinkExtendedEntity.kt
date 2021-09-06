package com.coinpaprika.apiclient.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkExtendedEntity(
    val url: String,
    val type: String,
    val stats: Map<String, Int>?
)