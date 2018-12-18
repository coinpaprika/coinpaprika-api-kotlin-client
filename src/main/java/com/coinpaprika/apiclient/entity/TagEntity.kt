/*
 * Created by Piotr Kostecki on 29.11.18 09:26
 * kontakt@piotrkostecki.pl
 *
 * Last modified 29.11.18 09:26
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TagEntity(val id: String,
                     val name: String,
                     val description: String? = "",
                     val type: String? = "",
                     @SerializedName("coin_counter") val coinCounter: Int,
                     @SerializedName("ico_counter") val icoCounter: Int,
                     val coins: List<String>? = emptyList()): Parcelable