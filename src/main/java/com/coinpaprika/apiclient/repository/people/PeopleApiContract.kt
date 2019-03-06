/*
 * Created by Piotr Kostecki on 09.01.19 12:06
 * kontakt@piotrkostecki.pl
 *
 * Last modified 09.01.19 12:06
 */

package com.coinpaprika.apiclient.repository.people

import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleApiContract {
    @GET("people/{id}/")
    fun getPerson(@Path("id") id: String): Observable<Response<PersonEntity>>

    @GET("people/{id}/twitter/")
    fun getTweets(@Path("id") id: String): Observable<Response<List<TweetEntity>>>
}