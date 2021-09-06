package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import retrofit2.http.GET

interface GlobalStatsService {
    @GET("global")
    suspend fun getGlobalStats(): GlobalStatsEntity
}