package com.coinpaprika.apiclient.repository.news

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.NewsEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class NewsApi constructor(
    private var retrofit: NewsApiContract = CoinpaprikaApiFactory()
        .client()
        .create(NewsApiContract::class.java)
) : NewsApiContract {

    override fun getNews(limit: Int): Observable<Response<List<NewsEntity>>> {
        return safeApiCallRaw { retrofit.getNews(limit) }
    }
}