package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class PositionEntity(
    @SerializedName("coin_id") val coinId: String,
    @SerializedName("coin_name") val coinName: String,
    @SerializedName("coin_symbol") val coinSymbol: String,
    val position: String
)