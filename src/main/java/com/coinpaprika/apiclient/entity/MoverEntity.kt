package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoverEntity(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @Json(name = "percent_change") val percentChange: Double
)