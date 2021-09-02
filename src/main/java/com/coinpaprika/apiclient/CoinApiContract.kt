package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface CoinApiContract {
    @GET("coins/{id}")
    suspend fun getCoin(@Path("id") id: String): CoinDetailsEntity

    @GET("coins")
    suspend fun getCoins(@Query("additional_fields") additionalFields: String? = null): List<CoinEntity>

    @GET("coins/{id}/events/")
    fun getEvents(@Path("id") id: String): Observable<Response<List<EventEntity>>>

    @GET("coins/{id}/exchanges/")
    fun getExchanges(@Path("id") id: String): Observable<Response<List<ExchangeEntity>>>

    @GET("coins/{id}/markets/")
    fun getMarkets(@Path("id") id: String, @Query("quotes") quotes: String): Observable<Response<List<MarketEntity>>>

    @GET("coins/{id}/twitter/")
    fun getTweets(@Path("id") id: String): Observable<Response<List<TweetEntity>>>

    @POST("coins/{id}/events")
    fun addEvent(@Path("id") cryptoId: String, @Body event: EventEntity): Observable<Response<Void>>
}