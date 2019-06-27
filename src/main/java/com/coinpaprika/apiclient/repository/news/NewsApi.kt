/*
 * Created by Piotr Kostecki on 09.01.19 12:49
 */

package com.coinpaprika.apiclient.repository.news

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.NewsEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class NewsApi constructor(
    context: Context,
    private var retrofit: NewsApiContract = CoinpaprikaApiFactory()
        .client()
        .create(NewsApiContract::class.java)
) : BaseApi(context), NewsApiContract {

    override fun getNews(limit: Int): Observable<Response<List<NewsEntity>>> {
        return safeApiCallRaw { retrofit.getNews(limit) }
    }
}