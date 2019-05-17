/*
 * Created by Piotr Kostecki on 09.01.19 12:47
 */

package com.coinpaprika.apiclient.repository.search

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.SearchEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.extensions.handleResponse
import io.reactivex.Observable
import retrofit2.Response

class SearchApi constructor(
    context: Context,
    private var retrofit: SearchApiContract = CoinpaprikaApiFactory()
        .client()
        .create(SearchApiContract::class.java)
) : BaseApi(context), SearchApiContract {

    override fun getSearches(query: String): Observable<Response<SearchEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getSearches(query).handleResponse(emitter)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }
}