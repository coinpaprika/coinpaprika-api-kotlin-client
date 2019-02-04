/*
 * Created by Piotr Kostecki on 12.12.18 13:07
 * kontakt@piotrkostecki.pl
 *
 * Last modified 12.12.18 13:07
 */

package com.coinpaprika.apiclient.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FiatEntity(val name: String,
                      val symbol: String): Parcelable