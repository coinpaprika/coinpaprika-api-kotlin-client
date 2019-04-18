/*
 * Created by Piotr Kostecki on 09.01.19 12:05
 */

package com.coinpaprika.apiclient.repository.tag

import com.coinpaprika.apiclient.entity.TagEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TagApiContract {
    @GET("tags/{id}?additional_fields=coins")
    fun getTag(@Path("id") id: String): Observable<Response<TagEntity>>

    @GET("tags?additional_fields=coins")
    fun getTags(): Observable<Response<List<TagEntity>>>
}