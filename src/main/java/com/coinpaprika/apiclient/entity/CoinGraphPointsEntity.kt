/*
 * Created by Piotr Kostecki on 07.12.18 10:05
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class CoinGraphPointsEntity(val usd: MutableList<GraphPointEntity>,
                                 @SerializedName("market_cap") val marketCap: MutableList<GraphPointEntity>,
                                 val volume: MutableList<GraphPointEntity>)