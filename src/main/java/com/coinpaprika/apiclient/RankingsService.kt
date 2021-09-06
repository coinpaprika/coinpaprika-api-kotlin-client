package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.TopMoversEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RankingsService {
    @GET("rankings/top10movers/")
    suspend fun getTop10Movers(
        @Query("type") type: String
    ): TopMoversEntity

    @GET("rankings/top-movers/")
    suspend fun getMovers(
        @Query("results_number") results: Int,
        @Query("marketcap_limit") range: String
    ): TopMoversEntity
}