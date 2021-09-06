package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.SearchEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/?c=currencies,icos,people,tags")
    suspend fun getSearches(@Query("q") query: String): SearchEntity
}