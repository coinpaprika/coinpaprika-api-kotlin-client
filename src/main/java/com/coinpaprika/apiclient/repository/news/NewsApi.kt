package com.coinpaprika.apiclient.repository.news

import com.coinpaprika.apiclient.entity.NewsEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class NewsApi(private var contract: NewsApiContract) : NewsApiContract {

    override fun getNews(limit: Int): Observable<Response<List<NewsEntity>>> {
        return safeApiCallRaw { contract.getNews(limit) }
    }
}