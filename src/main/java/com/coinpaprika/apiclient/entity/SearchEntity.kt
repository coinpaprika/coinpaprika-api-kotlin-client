package com.coinpaprika.apiclient.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchEntity(
    val currencies: List<CoinEntity>,
    val icos: List<IcosEntity>,
    val people: List<TeamMemberEntity>,
    val tags: List<TagEntity>
)