/*
 * Created by Piotr Kostecki on 09.01.19 12:47
 */

package com.coinpaprika.apiclient.repository.search

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.SearchEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
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
                    retrofit.getSearches(query)
                        .doOnNext {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onNext(it)
                                } else {
                                    when (it.code()) {
                                        429 -> emitter.onError(TooManyRequestsError())
                                        else -> emitter.onError(ServerConnectionError())
                                    }
                                }
                            }
                        }
                        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
                        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
                        .subscribe({}, { error -> error.printStackTrace() })
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