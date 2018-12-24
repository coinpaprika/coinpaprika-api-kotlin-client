/*
 * Created by Piotr Kostecki on 21.12.18 10:26
 * kontakt@piotrkostecki.pl
 *
 * Last modified 21.12.18 10:26
 */

package com.coinpaprika.apiclient.entity

data class SearchEntity(val currencies: List<TickerEntity>,
                        val icos: List<CoinEntity>,
                        val people: List<TeamMemberEntity>,
                        val tags: List<TagEntity>)