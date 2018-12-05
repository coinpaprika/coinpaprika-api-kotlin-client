/*
 * Created by Piotr Kostecki on 07.12.18 10:05
 * kontakt@piotrkostecki.pl
 *
 * Last modified 07.12.18 10:05
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class CoinGraphPointsEntity(val usd: List<GraphPointEntity>,
                                 @SerializedName("market_cap") val marketCap: List<GraphPointEntity>,
                                 val volume: List<GraphPointEntity>)