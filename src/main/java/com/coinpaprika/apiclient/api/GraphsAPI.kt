/*
 * Created by Piotr Kostecki on 23.11.18 15:06
 * kontakt@piotrkostecki.pl
 *
 * Last modified 23.11.18 15:06
 */

package com.coinpaprika.apiclient.api

import android.content.Context
import com.coinpaprika.apiclient.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import io.reactivex.Observable

open class GraphsAPI constructor(context: Context,
                                 private var retrofit: CoinpaprikaGraphsApiContract = CoinpaprikaApiFactory.graphs())
    : BaseApi(context) {

    open fun chartSvg(cryptocurrencyId: String, period: String): Observable<String> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getChartSVG(cryptocurrencyId, period)
                        .doOnNext {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onNext(it.body()!!)
                                    emitter.onComplete()
                                } else {
                                    emitter.onError(ServerConnectionError())
                                }
                            }
                        }
                        .subscribe({}, {error -> error.printStackTrace()})
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