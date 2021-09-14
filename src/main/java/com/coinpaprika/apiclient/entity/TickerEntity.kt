package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TickerEntity(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int = -1,
    @Json(name = "circulating_supply") val circulatingSupply: Long = 0,
    @Json(name = "total_supply") val totalSupply: Long = 0,
    @Json(name = "max_supply") val maxSupply: Long = 0,
    @Json(name = "beta_value") val betaValue: Double = 0.0,
    @Json(name = "last_updated") val lastUpdated: String? = "",
    var quotes: Map<String, QuoteEntity>? = emptyMap(),
    var tags: List<String>? = emptyList()
)