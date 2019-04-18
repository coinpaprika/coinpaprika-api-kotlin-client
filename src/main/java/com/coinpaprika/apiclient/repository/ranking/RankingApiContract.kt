/*
 * Created by Piotr Kostecki on 09.01.19 12:05
 */

package com.coinpaprika.apiclient.repository.ranking

import com.coinpaprika.apiclient.entity.TopMoversEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RankingApiContract {
    @GET("rankings/top10movers/")
    fun getTop10Movers(@Query("type") type: String): Observable<Response<TopMoversEntity>>

    @GET("rankings/top-movers/")
    fun getMovers(@Query("results_number") results: Int,
                  @Query("marketcap_limit") range: String
    ): Observable<Response<TopMoversEntity>>
}