/*
 * Created by Piotr Kostecki on 10.12.18 14:20
 * kontakt@piotrkostecki.pl
 *
 * Last modified 10.12.18 14:20
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class EventEntity(val date: String,
                       @SerializedName("date_to") val dateTo: String,
                       val name: String,
                       val description: String,
                       @SerializedName("is_conference") val isConference: Boolean,
                       val link: String,
                       @SerializedName("proof_image_link") val proofImageLink: String)