package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContractEntity(
    @Json(name = "contract") val contract: String,
    @Json(name = "platform") val platform: String,
    @Json(name = "type") val type: String
)