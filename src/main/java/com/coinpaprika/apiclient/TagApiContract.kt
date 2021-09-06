package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.TagEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface TagApiContract {
    @GET("tags/{id}?additional_fields=coins")
    suspend fun getTag(@Path("id") id: String): TagEntity

    @GET("tags?additional_fields=coins")
    suspend fun getTags(): List<TagEntity>
}