package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.api.CoinpaprikaApiContract
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoinpaprikaApiFactory {
    private const val BASE_URL = "https://api.coinpaprika.com/v1/"

    fun client(): CoinpaprikaApiContract {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClient()
                .addInterceptor(createLoggingInterceptor())
                .build())
            .build()
            .create(CoinpaprikaApiContract::class.java)
    }

    private fun createClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .writeTimeout(20000, TimeUnit.MILLISECONDS)
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .connectTimeout(15000, TimeUnit.MILLISECONDS)
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
    }

    private fun createLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}