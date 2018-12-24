/*
 * Created by Piotr Kostecki on 18.12.18 13:17
 * kontakt@piotrkostecki.pl
 *
 * Last modified 18.12.18 13:17
 */

package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class PersonEntity(val id: String,
                        val name: String,
                        val description: String?,
                        @SerializedName("teams_count") val teamMembers: Int,
                        val links: PersonLinks?,
                        val positions: List<PositionEntity>?)