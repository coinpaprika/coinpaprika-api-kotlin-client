/*
 * Created by Piotr Kostecki on 07.12.18 10:32
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class RawCoinGraphPointsEntity(val price: List<JsonArray>?,
                                    val usd: List<JsonArray>?,
                                    @SerializedName("market_cap") val marketCap: List<JsonArray>?,
                                    val volume: List<JsonArray>?
)