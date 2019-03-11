/*
 * Created by Piotr Kostecki on 13.12.18 12:13
 * kontakt@piotrkostecki.pl
 *
 * Last modified 13.12.18 12:13
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class RedditEntity(val data: RedditDataEntity) {
    data class RedditDataEntity(val ups: Int,
                                val downs: Int,
                                val title: String,
                                @SerializedName("selftext") val description: String?,
                                val author: String,
                                @SerializedName("created_utc") val createdTimestamp: Long,
                                val thumbnail: String?,
                                val permalink: String,
                                val url: String,
                                @SerializedName("subreddit_subscribers") val readers: Int,
                                @SerializedName("num_comments") val comments: Int)
}