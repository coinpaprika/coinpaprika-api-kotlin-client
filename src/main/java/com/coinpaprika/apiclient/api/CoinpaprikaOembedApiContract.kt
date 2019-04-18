/*
 * Created by Piotr Kostecki on 06.12.18 10:59
 */

package com.coinpaprika.apiclient.api

import com.coinpaprika.apiclient.entity.VideoEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinpaprikaOembedApiContract {
    @GET("oembed")
    fun getYoutubeVideo(@Query("url") url: String,
                        @Query("format") format: String): Observable<Response<VideoEntity>>

    @GET("oembed.json")
    fun getVimeoVideo(@Path("url") url: String): Observable<Response<VideoEntity>>
}