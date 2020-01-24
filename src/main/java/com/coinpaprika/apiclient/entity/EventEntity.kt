package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class EventEntity(
    @SerializedName("date") val startDate: String,
    @SerializedName("date_to") val endDate: String? = null,
    val name: String,
    val description: String?,
    @SerializedName("is_conference") val isConference: Boolean? = false,
    val link: String?,
    @SerializedName("proof_image_link") val proofImageLink: String? = null
)