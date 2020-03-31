/*
 * Created by Piotr Kostecki on 4/1/19 1:33 PM PM
 */

package com.coinpaprika.apiclient.repository.fiats

import com.coinpaprika.apiclient.entity.FiatEntity
import retrofit2.http.GET

interface FiatApiContract {
    @GET("fiats")
    suspend fun getFiats(): List<FiatEntity>
}