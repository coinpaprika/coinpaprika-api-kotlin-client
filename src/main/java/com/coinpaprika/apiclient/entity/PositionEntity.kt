package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PositionEntity(
    @Json(name = "coin_id") val coinId: String,
    @Json(name = "coin_name") val coinName: String,
    @Json(name = "coin_symbol") val coinSymbol: String,
    val position: String
)