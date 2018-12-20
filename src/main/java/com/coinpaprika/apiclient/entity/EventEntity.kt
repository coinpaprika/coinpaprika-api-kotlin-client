/*
 * Created by Piotr Kostecki on 10.12.18 14:20
 * kontakt@piotrkostecki.pl
 *
 * Last modified 10.12.18 14:20
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventEntity(@SerializedName("date") val startDate: String,
                       @SerializedName("date_to") val endDate: String?,
                       val name: String,
                       val description: String?,
                       @SerializedName("is_conference") val isConference: Boolean,
                       val link: String?,
                       @SerializedName("proof_image_link") val proofImageLink: String?): Parcelable