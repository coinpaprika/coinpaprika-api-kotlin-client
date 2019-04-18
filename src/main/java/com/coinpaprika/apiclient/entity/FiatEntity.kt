/*
 * Created by Piotr Kostecki on 12.12.18 13:07
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FiatEntity(val id: String,
                      val name: String,
                      val symbol: String): Parcelable