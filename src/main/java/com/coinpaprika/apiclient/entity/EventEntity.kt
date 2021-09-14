package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventEntity(
    @Json(name = "date") val startDate: String,
    @Json(name = "date_to") val endDate: String? = null,
    val name: String,
    val description: String?,
    @Json(name = "is_conference") val isConference: Boolean? = false,
    val link: String?,
    @Json(name = "proof_image_link") val proofImageLink: String? = null
)