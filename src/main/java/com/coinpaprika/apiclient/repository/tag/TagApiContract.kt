package com.coinpaprika.apiclient.repository.tag

import com.coinpaprika.apiclient.entity.TagEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TagApiContract {
    @GET("tags/{id}?additional_fields=coins")
    suspend fun getTag(@Path("id") id: String): TagEntity

    @GET("tags?additional_fields=coins")
    suspend fun getTags(): List<TagEntity>
}