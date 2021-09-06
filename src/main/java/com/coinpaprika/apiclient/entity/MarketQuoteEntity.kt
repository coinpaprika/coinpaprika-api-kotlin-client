package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketQuoteEntity(
    val price: Double,
    @Json(name = "volume_24h") val dailyVolume: Double,
)