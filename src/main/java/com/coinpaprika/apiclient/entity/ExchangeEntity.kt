/*
 * Created by Piotr Kostecki on 12.12.18 13:07
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExchangeEntity(val id: String,
                          val name: String,
                          @SerializedName("adjusted_volume_24h_share") val volume: Double,
                          val fiats: List<FiatEntity>,
                          val active: Boolean,
                          @SerializedName("adjusted_rank") val rankAdjusted: Int,
                          @SerializedName("reported_rank") val rankReported: Int,
                          val quotes: Map<String, QuoteEntity> = emptyMap()): Parcelable