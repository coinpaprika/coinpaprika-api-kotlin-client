/*
 * Created by Piotr Kostecki on 23.11.18 15:02
 * kontakt@piotrkostecki.pl
 *
 * Last modified 23.11.18 15:02
 */

package com.coinpaprika.apiclient.api

import com.coinpaprika.apiclient.entity.RawCoinGraphPointsEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinpaprikaGraphsApiContract {
    @GET("currency/chart/{cryptocurrencyId}/{period}/svg")
    fun getChartSVG(
        @Path("cryptocurrencyId") cryptocurrencyId: String,
        @Path("period") period: String
    ): Observable<Response<String>>

    @GET("currency/data/{cryptocurrencyId}/{period}/")
    fun getGraphDataGivenPeriod(
        @Path("cryptocurrencyId") cryptocurrencyId: String,
        @Path("period") period: String
    ): Observable<Response<List<RawCoinGraphPointsEntity>>>

    @GET("currency/data/{cryptocurrencyId}/dates/{tsFrom}/{tsTo}/")
    fun getGraphDataCustomPeriod(
        @Path("cryptocurrencyId") cryptocurrencyId: String,
        @Path("tsFrom") tsFrom: Long,
        @Path("tsTo") tsTo: Long
    ): Observable<Response<List<RawCoinGraphPointsEntity>>>
}