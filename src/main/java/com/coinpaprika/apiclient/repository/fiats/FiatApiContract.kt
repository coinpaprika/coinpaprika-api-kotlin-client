/*
 * Created by Piotr Kostecki on 4/1/19 1:33 PM PM
 */

package com.coinpaprika.apiclient.repository.fiats

import com.coinpaprika.apiclient.entity.FiatEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface FiatApiContract {
    @GET("fiats")
    fun getFiats(): Observable<Response<List<FiatEntity>>>
}