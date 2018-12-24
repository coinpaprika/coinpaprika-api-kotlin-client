/*
 * Created by Piotr Kostecki on 05.12.18 10:58
 * kontakt@piotrkostecki.pl
 *
 * Last modified 05.12.18 10:58
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamMemberEntity(val id: String,
                            val name: String,
                            @SerializedName("position") val role: String?): Parcelable