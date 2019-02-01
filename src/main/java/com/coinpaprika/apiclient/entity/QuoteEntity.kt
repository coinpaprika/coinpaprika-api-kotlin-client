/*
 * Created by Piotr Kostecki on 20.11.18 10:33
 * kontakt@piotrkostecki.pl
 *
 * Last modified 20.11.18 10:33
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuoteEntity(@SerializedName("price") val price: Double,
                       @SerializedName("volume_24h") val dailyVolume: Double,
                       @SerializedName("volume_24h_change_24h") val dailyVolumeDailyChange: Double,
                       @SerializedName("market_cap") val marketCap: Long,
                       @SerializedName("market_cap_change_24h") val marketCapDailyChange: Double,
                       @SerializedName("percent_change_1h") val percentChange1h: Double,
                       @SerializedName("percent_change_12h") val percentChange12h: Double,
                       @SerializedName("percent_change_24h") val percentChange24h: Double,
                       @SerializedName("percent_change_7d") val percentChange7d: Double,
                       @SerializedName("percent_change_30d") val percentChange30d: Double,
                       @SerializedName("percent_change_1y") val percentChange1y: Double,
                       @SerializedName("ath_price") val athPrice: Double,
                       @SerializedName("ath_date") val athDate: String? = "",
                       @SerializedName("percent_from_price_ath") val percentFromPriceAth: Double,
                       @SerializedName("adjusted_volume_24h_share") val volumeAdjusted: Double,
                       @SerializedName("reported_volume_24h_share") val volumeReported: Double): Parcelable