/*
 * Created by Piotr Kostecki on 05.12.18 10:59
 * kontakt@piotrkostecki.pl
 *
 * Last modified 05.12.18 10:59
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinksEntity(val explorer: List<String>?,
                       val facebook: List<String>?,
                       val reddit: List<String>?,
                       @SerializedName("source_code") val sourceCode: List<String>?,
                       val website: List<String>?,
                       val youtube: List<String>?,
                       val github: List<LinkEntity>?,
                       val linkedin: List<LinkEntity>?,
                       val medium: List<LinkEntity>?,
                       val twitter: List<LinkEntity>?): Parcelable