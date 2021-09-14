package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GlobalStatsEntity(
    @Json(name = "market_cap_usd") val marketCapUsd: Long,
    @Json(name = "market_cap_change_24h") val dailyMarketCapChange: Double,
    @Json(name = "market_cap_ath_value") val marketCapAthValue: Long,
    @Json(name = "market_cap_ath_date") val marketCapAthDate: String,
    @Json(name = "volume_24h_usd") val dailyVolumeUsd: Long,
    @Json(name = "volume_24h_change_24h") val dailyVolumeChange: Double,
    @Json(name = "volume_24h_ath_value") val volumeAthValue: Long,
    @Json(name = "volume_24h_ath_date") val volumeAthDate: String,
    @Json(name = "bitcoin_dominance_percentage") val btcDominancePercentage: Double,
    @Json(name = "cryptocurrencies_number") val cryptocurrenciesAmount: Int,
    @Json(name = "last_updated") val lastUpdated: Long
)