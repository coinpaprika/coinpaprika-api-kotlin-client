package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class GlobalStatsEntity(@SerializedName("market_cap_usd") val marketCapUsd: Long,
                             @SerializedName("market_cap_change_24h") val dailyMarketCapChange: Double,
                             @SerializedName("market_cap_ath_value") val marketCapAthValue: Long,
                             @SerializedName("volume_24h_usd") val dailyVolumeUsd: Long,
                             @SerializedName("volume_24h_change_24h") val dailyVolumeChange: Double,
                             @SerializedName("volume_24h_ath_value") val volumeAthValue: Long,
                             @SerializedName("bitcoin_dominance_percentage") val btcDominancePercentage: Double,
                             @SerializedName("cryptocurrencies_number") val cryptocurrenciesAmount: Int,
                             @SerializedName("last_updated") val lastUpdated: Long)