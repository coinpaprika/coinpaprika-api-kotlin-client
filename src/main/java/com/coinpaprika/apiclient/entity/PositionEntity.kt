/*
 * Created by Piotr Kostecki on 18.12.18 13:20
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PositionEntity(@SerializedName("coin_id") val coinId: String,
                          @SerializedName("coin_name") val coinName: String,
                          @SerializedName("coin_symbol") val coinSymbol: String,
                          val position: String): Parcelable