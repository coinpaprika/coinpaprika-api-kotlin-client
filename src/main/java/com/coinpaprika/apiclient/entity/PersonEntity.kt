package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonEntity(
    val id: String,
    val name: String,
    val description: String?,
    @Json(name = "teams_count") val teamMembers: Int,
    val links: PersonLinks?,
    val positions: List<PositionEntity>?
)