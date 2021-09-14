package com.coinpaprika.apiclient.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopMoversEntity(
    val gainers: List<MoverEntity>,
    val losers: List<MoverEntity>
)