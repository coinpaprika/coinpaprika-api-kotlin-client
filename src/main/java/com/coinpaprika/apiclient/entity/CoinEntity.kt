package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinEntity(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "symbol") val symbol: String,
    @Json(name = "rank") val rank: Int,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "is_active") val isActive: Boolean,
    @Json(name = "type") val type: CoinType,
    @Json(name = "contracts") val contracts: List<ContractEntity>?
)