package com.coinpaprika.apiclient

import android.content.Context
import com.coinpaprika.apiclient.api.CoinpaprikaGraphsApiContract
import com.coinpaprika.apiclient.api.CoinpaprikaOembedApiContract
import com.coinpaprika.apiclient.api.CoinpaprikaRedditApiContract
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class CoinpaprikaApiFactory(context: Context) {
    companion object {
        private const val BASE_URL = "https://api.coinpaprika.com/v1/"
        private const val GRAPHS_URL = "https://graphs.coinpaprika.com/"
        const val REDDIT_URL = "https://www.reddit.com/"
    }

    private var cacheSize = 10 * 1024 * 1024 // 10 MB
    private var cache = Cache(context.cacheDir, cacheSize.toLong())

    fun client(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClient()
                .addInterceptor(createLoggingInterceptor())
                .build())
            .build()
    }

    fun graphs(): CoinpaprikaGraphsApiContract {
        return Retrofit.Builder()
            .baseUrl(GRAPHS_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClient()
                .cache(cache)
                .addInterceptor{
                    val request = it.request().newBuilder().header("Cache-Control", "public, max-age=" + 60 * 5).build()
                    it.proceed(request)
                }
                .addInterceptor(createLoggingInterceptor())
                .build())
            .build()
            .create(CoinpaprikaGraphsApiContract::class.java)
    }

    fun oembed(baseUrl: String): CoinpaprikaOembedApiContract {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClient()
                .addInterceptor(createLoggingInterceptor())
                .build())
            .build()
            .create(CoinpaprikaOembedApiContract::class.java)
    }

    fun reddit(): CoinpaprikaRedditApiContract {
        return Retrofit.Builder()
            .baseUrl(REDDIT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClient()
                .addInterceptor(createLoggingInterceptor())
                .build())
            .build()
            .create(CoinpaprikaRedditApiContract::class.java)
    }

    private fun createClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
//            .cache(cache)
            .protocols(listOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
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