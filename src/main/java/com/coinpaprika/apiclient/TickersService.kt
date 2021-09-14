package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.TickerEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TickersService {
    @GET("tickers/{id}/")
    suspend fun getTicker(
        @Path("id") id: String,
        @Query("quotes") quotes: String
    ): TickerEntity

    @GET("tickers")
    suspend fun getTickers(
        @Query("quotes") quotes: String,
        @Query("limit") limit: Int? = null
    ): List<TickerEntity>
}