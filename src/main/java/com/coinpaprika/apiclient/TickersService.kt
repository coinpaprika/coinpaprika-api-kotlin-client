package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.TickerEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TickersService {
    @GET("tickers/{id}/")
    suspend fun getTicker(
        @Path("id") id: String,
        @Query("quotes") quotes: String,
        @QueryMap options: Map<String, String> = emptyMap()
    ): TickerEntity

    @GET("tickers")
    suspend fun getTickers(
        @Query("quotes") quotes: String,
        @Query("page") page: Int? = null,
        @QueryMap options: Map<String, String> = emptyMap()
    ): List<TickerEntity>
}