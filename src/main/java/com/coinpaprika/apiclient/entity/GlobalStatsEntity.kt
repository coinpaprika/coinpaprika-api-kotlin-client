package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class GlobalStatsEntity(@SerializedName("market_cap_usd") val marketCapUsd: Long,
                             @SerializedName("volume_24h_usd") val dailyVolumeUsd: Long,
                             @SerializedName("bitcoin_dominance_percentage") val btcDominancePercentage: Double,
                             @SerializedName("cryptocurrencies_number") val cryptocurrenciesAmount: Int,
                             @SerializedName("last_updated") val lastUpdated: Long)