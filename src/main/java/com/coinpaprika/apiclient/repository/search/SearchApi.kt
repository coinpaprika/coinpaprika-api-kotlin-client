/*
 * Created by Piotr Kostecki on 09.01.19 12:47
 */

package com.coinpaprika.apiclient.repository.search

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.SearchEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class SearchApi constructor(
    private var retrofit: SearchApiContract = CoinpaprikaApiFactory()
        .client()
        .create(SearchApiContract::class.java)
) : SearchApiContract {

    override fun getSearches(query: String): Observable<Response<SearchEntity>> {
        return safeApiCallRaw { retrofit.getSearches(query) }
    }
}