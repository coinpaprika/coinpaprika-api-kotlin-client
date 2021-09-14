package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketEntity(
    @Json(name = "exchange_id") val exchangeId: String,
    @Json(name = "exchange_name") val exchangeName: String,
    val pair: String,
    @Json(name = "base_currency_id") val baseCurrencyId: String,
    @Json(name = "base_currency_name") val baseCurrencyName: String,
    @Json(name = "quote_currency_id") val quoteCurrencyId: String,
    @Json(name = "quote_currency_name") val quoteCurrencyName: String,
    @Json(name = "market_url") val marketUrl: String?,
    val category: String,
    @Json(name = "fee_type") val feeType: String,
    val outlier: Boolean,
    @Json(name = "adjusted_volume_24h_share") val volumeAdjusted: Double?,
    @Json(name = "reported_volume_24h_share") val volumeReported: Double?,
    val quotes: Map<String, MarketQuoteEntity>,
    @Json(name = "last_updated") val lastUpdated: String
) {

    fun getPairedConversionSymbol(): String {
        return pair.substringAfter("/")
    }
}