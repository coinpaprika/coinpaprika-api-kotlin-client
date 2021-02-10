/*
 * Created by Piotr Kostecki on 5/16/19 4:05 PM
 */

package com.coinpaprika.apiclient.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun defaultClient(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
}

class CoinpaprikaApiFactory(private val client: OkHttpClient = defaultClient().build()) {
    companion object {
        private const val BASE_URL = "https://api.coinpaprika.com/v1/"
    }

    fun client(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}
