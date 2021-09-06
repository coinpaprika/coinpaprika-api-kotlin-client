package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagEntity(
    val id: String,
    val name: String,
    val description: String? = "",
    val type: String? = "",
    @Json(name = "coin_counter") val coinCounter: Int,
    @Json(name = "ico_counter") val icoCounter: Int,
    val coins: List<String>? = emptyList()
)