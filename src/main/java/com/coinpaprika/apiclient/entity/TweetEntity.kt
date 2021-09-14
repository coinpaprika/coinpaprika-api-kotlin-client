package com.coinpaprika.apiclient.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TweetEntity(
    val date: String,
    @Json(name = "user_name") val userName: String,
    @Json(name = "user_image_link") val imageLink: String,
    val status: String,
    @Json(name = "is_retweet") val isRetweet: Boolean,
    @Json(name = "retweet_count") val retweetCount: Int,
    @Json(name = "like_count") val likeCount: Int,
    @Json(name = "status_link") val statusLink: String,
    @Json(name = "media_link") val mediaLink: String?
)