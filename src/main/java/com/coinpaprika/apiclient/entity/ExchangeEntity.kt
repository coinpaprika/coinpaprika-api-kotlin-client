/*
 * Created by Piotr Kostecki on 12.12.18 13:07
 * kontakt@piotrkostecki.pl
 *
 * Last modified 12.12.18 13:07
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class ExchangeEntity(val id: String,
                          val name: String,
                          @SerializedName("adjusted_volume_24h_share") val volume: Double,
                          val fiats: List<FiatEntity>)