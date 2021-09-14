package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditEntity(val data: RedditDataEntity) {
    data class RedditDataEntity(
        val ups: Int,
        val downs: Int,
        val title: String,
        @Json(name = "selftext") val description: String?,
        val author: String,
        @Json(name = "created_utc") val createdTimestamp: Long,
        val thumbnail: String?,
        val permalink: String,
        val url: String,
        @Json(name = "subreddit_subscribers") val readers: Int,
        @Json(name = "num_comments") val comments: Int
    )
}