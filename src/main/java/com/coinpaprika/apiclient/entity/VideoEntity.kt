/*
 * Created by Piotr Kostecki on 06.12.18 11:02
 * kontakt@piotrkostecki.pl
 *
 * Last modified 06.12.18 11:02
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoEntity(@SerializedName("author_url") val authorUrl: String,
                       @SerializedName("author_name") val authorName: String,
                       val height: Int,
                       val width: Int,
                       val type: String,
                       @SerializedName("provider_url") val providerUrl: String,
                       @SerializedName("provider_name") val providerName: String,
                       val html: String,
                       val title: String,
                       @SerializedName("thumbnail_url") val thumbnailUrl: String,
                       @SerializedName("thumbnail_height") val thumbnailHeight: Int,
                       @SerializedName("thumbnail_width") val thumbnailWidth: Int,
                       val version: String): Parcelable