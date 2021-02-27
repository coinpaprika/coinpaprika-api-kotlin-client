/*
 * Created by Piotr Kostecki on 09.01.19 12:47
 */

package com.coinpaprika.apiclient.repository.search

import com.coinpaprika.apiclient.entity.SearchEntity

class SearchApi(private var contract: SearchApiContract) : SearchApiContract {

    override suspend fun getSearches(query: String): SearchEntity {
        return contract.getSearches(query)
    }
}