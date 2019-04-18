/*
 * Created by Piotr Kostecki on 28.12.18 17:00
 */

package com.coinpaprika.apiclient.entity

data class TopMoversEntity(val gainers: List<MoverEntity>,
                           val losers: List<MoverEntity>)