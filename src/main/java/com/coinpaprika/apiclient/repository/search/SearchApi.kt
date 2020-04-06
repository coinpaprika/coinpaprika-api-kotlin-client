/*
 * Created by Piotr Kostecki on 09.01.19 12:47
 */

package com.coinpaprika.apiclient.repository.search

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.SearchEntity

class SearchApi constructor(
    private var retrofit: SearchApiContract = CoinpaprikaApiFactory()
        .client()
        .create(SearchApiContract::class.java)
) : SearchApiContract {

    override suspend fun getSearches(query: String): SearchEntity {
        return retrofit.getSearches(query)
    }
}