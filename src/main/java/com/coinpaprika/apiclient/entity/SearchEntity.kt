/*
 * Created by Piotr Kostecki on 21.12.18 10:26
 * kontakt@piotrkostecki.pl
 *
 * Last modified 21.12.18 10:26
 */

package com.coinpaprika.apiclient.entity

data class SearchEntity(val currencies: MutableList<TickerEntity>,
                        val icos: MutableList<CoinEntity>,
                        val people: MutableList<TeamMemberEntity>,
                        val tags: MutableList<TagEntity>)