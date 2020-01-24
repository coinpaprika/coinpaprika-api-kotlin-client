package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class TeamMemberEntity(
    val id: String,
    val name: String,
    @SerializedName("position") val role: String?
)