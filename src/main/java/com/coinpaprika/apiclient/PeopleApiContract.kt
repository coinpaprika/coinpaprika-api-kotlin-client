package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleApiContract {
    @GET("people/{id}/")
    fun getPerson(@Path("id") id: String): Single<PersonEntity>

    @GET("people/{id}/twitter/")
    fun getTweets(@Path("id") id: String): Single<List<TweetEntity>>
}