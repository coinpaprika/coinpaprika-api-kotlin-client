/*
 * Created by Piotr Kostecki on 09.01.19 12:03
 */

package com.coinpaprika.apiclient.repository.ticker

import com.coinpaprika.apiclient.entity.TickerEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TickerApiContract {
    @GET("tickers/{id}/")
    fun getTicker(@Path("id") id: String, @Query("quotes") quotes: String = "USD,BTC,ETH,PLN"): Observable<Response<TickerEntity>>

    @GET("tickers")
    fun getTickers(@Query("quotes") quotes: String = "USD,BTC,ETH"): Observable<Response<List<TickerEntity>>>
}