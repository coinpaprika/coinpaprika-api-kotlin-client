/*
 * Created by Piotr Kostecki on 05.12.18 11:00
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WhitepaperEntity(val link: String?,
                            val thumbnail: String?): Parcelable