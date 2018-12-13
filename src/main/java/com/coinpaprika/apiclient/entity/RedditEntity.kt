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
                                val title: String,
                                val thumbnail: String?,
                                val permalink: String,
                                @SerializedName("subreddit_subscribers") val readers: Int,
                                @SerializedName("num_comments") val comments: Int)
}