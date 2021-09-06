package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamMemberEntity(
    val id: String,
    val name: String,
    @Json(name = "position") val role: String?
)