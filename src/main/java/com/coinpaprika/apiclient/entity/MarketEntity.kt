/*
 * Created by Piotr Kostecki on 12.12.18 13:09
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class MarketEntity(@SerializedName("exchange_id") val exchangeId: String,
                        @SerializedName("exchange_name") val exchangeName: String,
                        val pair: String,
                        @SerializedName("base_currency_id") val baseCurrencyId: String,
                        @SerializedName("base_currency_name") val baseCurrencyName: String,
                        @SerializedName("quote_currency_id") val quoteCurrencyId: String,
                        @SerializedName("quote_currency_name") val quoteCurrencyName: String,
                        @SerializedName("market_url") val marketUrl: String,
                        val category: String,
                        @SerializedName("fee_type") val feeType: String,
                        val outlier: Boolean,
                        @SerializedName("adjusted_volume_24h_share") val volumeAdjusted: Double?,
                        @SerializedName("reported_volume_24h_share") val volumeReported: Double?,
                        val quotes: Map<String, QuoteEntity>,
                        @SerializedName("last_updated") val lastUpdated: String) {

    fun getPairedConversionSymbol(): String {
        return pair.substringAfter("/")
    }
}