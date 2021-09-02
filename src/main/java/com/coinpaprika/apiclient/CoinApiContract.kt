package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.CoinDetailsEntity
import com.coinpaprika.apiclient.entity.CoinEntity
import com.coinpaprika.apiclient.entity.EventEntity
import com.coinpaprika.apiclient.entity.ExchangeEntity
import com.coinpaprika.apiclient.entity.MarketEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApiContract {
    @GET("coins/{id}")
    suspend fun getCoin(
        @Path("id") id: String
    ): CoinDetailsEntity

    @GET("coins")
    suspend fun getCoins(
        @Query("additional_fields") additionalFields: String? = null
    ): List<CoinEntity>

    @GET("coins/{id}/events/")
    fun getEvents(
        @Path("id") id: String
    ): Single<List<EventEntity>>

    @GET("coins/{id}/exchanges/")
    suspend fun getExchanges(
        @Path("id") id: String
    ): List<ExchangeEntity>

    @GET("coins/{id}/markets/")
    fun getMarkets(
        @Path("id") id: String,
        @Query("quotes") quotes: String
    ): Single<List<MarketEntity>>

    @GET("coins/{id}/twitter/")
    fun getTweets(
        @Path("id") id: String
    ): Single<List<TweetEntity>>

    @POST("coins/{id}/events")
    fun addEvent(
        @Path("id") cryptoId: String,
        @Body event: EventEntity
    ): Completable
}