/*
 * Created by Piotr Kostecki on 13.12.18 13:29
 */

package com.coinpaprika.apiclient.api

import com.coinpaprika.apiclient.entity.RedditResponseEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinpaprikaRedditApiContract {
    @GET("r/{subreddit}/hot.json")
    fun getHot(@Path("subreddit") subreddit: String): Observable<Response<RedditResponseEntity>>

    @GET("r/{subreddit}/rising.json")
    fun getRising(@Path("subreddit") subreddit: String): Observable<Response<RedditResponseEntity>>

    @GET("r/{subreddit}/new.json")
    fun getNew(@Path("subreddit") subreddit: String): Observable<Response<RedditResponseEntity>>
}