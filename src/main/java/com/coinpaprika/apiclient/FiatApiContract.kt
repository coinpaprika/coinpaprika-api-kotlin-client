package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.FiatEntity
import retrofit2.http.GET

interface FiatApiContract {
    @GET("fiats")
    suspend fun getFiats(): List<FiatEntity>
}