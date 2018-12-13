package com.coinpaprika.apiclient.api

import com.coinpaprika.apiclient.entity.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinpaprikaApiContract {
    @GET("tickers")
    fun getTickers(): Observable<Response<List<TickerEntity>>>

    @GET("coins/{id}")
    fun getCoin(@Path("id") id: String): Observable<Response<CoinEntity>>

    @GET("coins")
    fun getCoins(): Observable<Response<List<CoinEntity>>>

    @GET("coins/{id}/events/")
    fun getEvents(@Path("id") id: String): Observable<Response<List<EventEntity>>>

    @GET("coins/{id}/exchanges/")
    fun getExchanges(@Path("id") id: String): Observable<Response<List<ExchangeEntity>>>

    @GET("coins/{id}/markets/")
    fun getMarkets(@Path("id") id: String): Observable<Response<List<MarketEntity>>>

    @GET("global")
    fun getGlobal(): Observable<Response<GlobalStatsEntity>>

    @GET("tags?additional_fields=coins")
    fun getTags(): Observable<Response<List<TagEntity>>>

    @GET("coins/{id}/twitter/")
    fun getTweets(@Path("id") id: String): Observable<Response<List<TweetEntity>>>
}