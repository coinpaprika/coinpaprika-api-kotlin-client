package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class TagEntity(
    val id: String,
    val name: String,
    val description: String? = "",
    val type: String? = "",
    @SerializedName("coin_counter") val coinCounter: Int,
    @SerializedName("ico_counter") val icoCounter: Int,
    val coins: List<String>? = emptyList()
)