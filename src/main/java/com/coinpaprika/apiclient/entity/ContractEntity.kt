package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class ContractEntity(
    @SerializedName("contract") val contract: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("type") val type: String
)