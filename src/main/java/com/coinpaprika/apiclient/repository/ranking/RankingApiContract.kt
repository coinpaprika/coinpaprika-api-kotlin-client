/*
 * Created by Piotr Kostecki on 09.01.19 12:05
 * kontakt@piotrkostecki.pl
 *
 * Last modified 09.01.19 12:05
 */

package com.coinpaprika.apiclient.repository.ranking

import com.coinpaprika.apiclient.entity.TopMoversEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface RankingApiContract {
    @GET("rankings/top10movers/")
    fun getMovers(): Observable<Response<TopMoversEntity>>
}