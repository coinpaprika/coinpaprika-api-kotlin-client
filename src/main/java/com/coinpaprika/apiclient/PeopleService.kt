package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleService {
    @GET("people/{id}/")
    suspend fun getPerson(@Path("id") id: String): PersonEntity

    @GET("people/{id}/twitter/")
    suspend fun getTweets(@Path("id") id: String): List<TweetEntity>
}