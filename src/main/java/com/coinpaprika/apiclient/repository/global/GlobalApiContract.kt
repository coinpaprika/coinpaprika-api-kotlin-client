/*
 * Created by Piotr Kostecki on 09.01.19 12:54
 */

package com.coinpaprika.apiclient.repository.global

import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface GlobalApiContract {
    @GET("global")
    fun getGlobal(): Observable<Response<GlobalStatsEntity>>
}