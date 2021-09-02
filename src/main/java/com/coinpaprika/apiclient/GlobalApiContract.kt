package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import io.reactivex.Single
import retrofit2.http.GET

interface GlobalApiContract {
    @GET("global")
    fun getGlobal(): Single<GlobalStatsEntity>
}