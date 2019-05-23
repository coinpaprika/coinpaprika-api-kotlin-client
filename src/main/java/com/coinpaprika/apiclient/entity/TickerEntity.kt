package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TickerEntity(val id: String,
                        val name: String,
                        val symbol: String,
                        val rank: Int = -1,
                        @SerializedName("circulating_supply") val circulatingSupply: Long = 0,
                        @SerializedName("total_supply") val totalSupply: Long = 0,
                        @SerializedName("max_supply") val maxSupply: Long = 0,
                        @SerializedName("beta_value") val betaValue: Double = 0.0,
                        @SerializedName("last_updated") val lastUpdated: String? = "",
                        var quotes: Map<String, QuoteEntity>? = emptyMap(),
                        var tags: MutableList<String>? = mutableListOf()): Parcelable