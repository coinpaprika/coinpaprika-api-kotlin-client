/*
 * Created by Piotr Kostecki on 10.12.18 14:20
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventEntity(@SerializedName("date") val startDate: String,
                       @SerializedName("date_to") val endDate: String? = null,
                       val name: String,
                       val description: String?,
                       @SerializedName("is_conference") val isConference: Boolean? = false,
                       val link: String?,
                       @SerializedName("proof_image_link") val proofImageLink: String? = null): Parcelable