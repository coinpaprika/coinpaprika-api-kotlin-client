/*
 * Created by Piotr Kostecki on 01.02.19 15:45
 */

package com.coinpaprika.apiclient.repository.exchange

import com.coinpaprika.apiclient.entity.ExchangeEntity
import com.coinpaprika.apiclient.entity.MarketEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApiContract{
    @GET("exchanges/")
    fun getExchanges(): Observable<Response<List<ExchangeEntity>>>

    @GET("exchanges/{exchangeId}/markets/")
    fun getMarkets(@Path("exchangeId") exchangeId: String): Observable<Response<List<MarketEntity>>>
}