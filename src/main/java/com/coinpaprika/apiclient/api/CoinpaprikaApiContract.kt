package com.coinpaprika.apiclient.api

import com.coinpaprika.apiclient.entity.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinpaprikaApiContract {
    @GET("tickers/{id}/")
    fun getTicker(@Path("id") id: String): Observable<Response<TickerEntity>>

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

    @GET("rankings/top10movers/")
    fun getMovers(): Observable<Response<TopMoversEntity>>

    @GET("global")
    fun getGlobal(): Observable<Response<GlobalStatsEntity>>

    @GET("tags/{id}?additional_fields=coins")
    fun getTag(@Path("id") id: String): Observable<Response<TagEntity>>

    @GET("tags?additional_fields=coins")
    fun getTags(): Observable<Response<List<TagEntity>>>

    @GET("coins/{id}/twitter/")
    fun getTweets(@Path("id") id: String): Observable<Response<List<TweetEntity>>>

    @GET("people/{id}/")
    fun getPerson(@Path("id") id: String): Observable<Response<PersonEntity>>

    @GET("search/?c=currencies,icos,people,tags")
    fun getSearches(@Query("q") query: String): Observable<Response<SearchEntity>>

    @GET("news/latest/")
    fun getNews(@Query("limit") limit: Int): Observable<Response<List<NewsEntity>>>
}