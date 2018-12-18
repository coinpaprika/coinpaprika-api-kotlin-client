/*
 * Created by Piotr Kostecki on 18.12.18 14:43
 * kontakt@piotrkostecki.pl
 *
 * Last modified 18.12.18 14:43
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinkEntity(val url: String,
                      val followers: Int?): Parcelable