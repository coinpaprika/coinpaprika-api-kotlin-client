/*
 * Created by Piotr Kostecki on 19.12.18 09:58
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonLinks(val explorer: List<String>?,
                       val facebook: List<String>?,
                       val reddit: List<String>?,
                       @SerializedName("source_code") val sourceCode: List<String>?,
                       val website: List<String>?,
                       val youtube: List<String>?,
                       val github: List<PersonSocialMedia>?,
                       val linkedin: List<PersonSocialMedia>?,
                       val medium: List<PersonSocialMedia>?,
                       val twitter: List<PersonSocialMedia>?): Parcelable