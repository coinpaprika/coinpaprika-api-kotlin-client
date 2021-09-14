package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonLinks(
    val explorer: List<String>?,
    val facebook: List<String>?,
    val reddit: List<String>?,
    @Json(name = "source_code") val sourceCode: List<String>?,
    val website: List<String>?,
    val youtube: List<String>?,
    val github: List<PersonSocialMedia>?,
    val linkedin: List<PersonSocialMedia>?,
    val medium: List<PersonSocialMedia>?,
    val twitter: List<PersonSocialMedia>?
)