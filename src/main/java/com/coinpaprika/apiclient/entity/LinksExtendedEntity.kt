package com.coinpaprika.apiclient.entity

data class LinkExtendedEntity(
    val url: String,
    val type: String,
    val stats: Map<String, Int>?
)