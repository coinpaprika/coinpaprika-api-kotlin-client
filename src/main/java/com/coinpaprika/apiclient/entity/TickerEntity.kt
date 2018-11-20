package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TickerEntity(val id: String,
                        val name: String,
                        val symbol: String,
                        val rank: Int,
                        @SerializedName("circulating_supply") val circulatingSupply: Long,
                        @SerializedName("total_supply") val totalSupply: Long,
                        @SerializedName("max_supply") val maxSupply: Long,
                        @SerializedName("beta_value") val betaValue: Double,
                        @SerializedName("last_updated") val lastUpdated: String,
                        val quotes: Map<String, QuoteEntity>): Parcelable