package com.coinpaprika.apiclient.repository.ticker

import com.coinpaprika.apiclient.entity.TickerEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TickerApiContract {
    @GET("tickers/{id}/")
    suspend fun getTicker(@Path("id") id: String, @Query("quotes") quotes: String = "USD,BTC,ETH,PLN"): Response<TickerEntity>

    @GET("tickers")
    suspend fun getTickers(@Query("quotes") quotes: String = "USD,BTC,ETH"): Response<List<TickerEntity>>
}