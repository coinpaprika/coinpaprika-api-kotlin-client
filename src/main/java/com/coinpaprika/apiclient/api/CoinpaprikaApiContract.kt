package com.coinpaprika.apiclient.api

import com.coinpaprika.apiclient.entity.CoinEntity
import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import com.coinpaprika.apiclient.entity.TagEntity
import com.coinpaprika.apiclient.entity.TickerEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface CoinpaprikaApiContract {
    @GET("tickers")
    fun getTickers(): Observable<Response<List<TickerEntity>>>

    @GET("coins")
    fun getCoins(): Observable<Response<List<CoinEntity>>>

    @GET("global")
    fun getGlobal(): Observable<Response<GlobalStatsEntity>>

    @GET("tags?additional_fields=coins")
    fun getTags(): Observable<Response<List<TagEntity>>>
}