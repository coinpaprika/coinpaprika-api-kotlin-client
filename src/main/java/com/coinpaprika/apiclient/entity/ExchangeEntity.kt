package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeEntity(
    val id: String,
    val name: String,
    @Json(name = "adjusted_volume_24h_share") val volume: Double,
    val fiats: List<FiatEntity>,
    val active: Boolean,
    @Json(name = "adjusted_rank") val rankAdjusted: Int,
    @Json(name = "reported_rank") val rankReported: Int,
    val quotes: Map<String, QuoteEntity> = emptyMap()
)