package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class TickerEntity(val id: String,
                        val name: String,
                        val symbol: String,
                        val rank: Int,
                        @SerializedName("price_usd") val priceUsd: Double,
                        @SerializedName("price_btc") val priceBtc: Double,
                        @SerializedName("volume_24h_usd") val dailyVolumeUsd: Long,
                        @SerializedName("market_cap_usd") val marketCapUsd: Long,
                        @SerializedName("circulating_supply") val circulatingSupply: Long,
                        @SerializedName("total_supply") val totalSupply: Long,
                        @SerializedName("max_supply") val maxSupply: Long,
                        @SerializedName("percent_change_1h") val percentChange1h: Double,
                        @SerializedName("percent_change_24h") val percentChange24h: Double,
                        @SerializedName("percent_change_7d") val percentChange7d: Double,
                        @SerializedName("last_updated") val lastUpdated: String)