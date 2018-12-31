/*
 * Created by Piotr Kostecki on 23.11.18 15:06
 * kontakt@piotrkostecki.pl
 *
 * Last modified 23.11.18 15:06
 */

package com.coinpaprika.apiclient.api

import android.content.Context
import com.coinpaprika.apiclient.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.RawCoinGraphPointsEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import io.reactivex.Observable

open class GraphsAPI constructor(context: Context,
                                 private var retrofit: CoinpaprikaGraphsApiContract = CoinpaprikaApiFactory(context).graphs())
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
                        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
                        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
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

    open fun currencyDataDefinedPeriod(cryptocurrencyId: String, period: String): Observable<RawCoinGraphPointsEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getCurrencyDataDefinedPeriod(cryptocurrencyId, period)
                        .doOnNext {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onNext(it.body()!![0])
                                    emitter.onComplete()
                                } else {
                                    emitter.onError(ServerConnectionError())
                                }
                            }
                        }
                        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
                        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
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

    open fun currencyDataCustomPeriod(cryptocurrencyId: String, fromTs: Long, toTs: Long): Observable<RawCoinGraphPointsEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getCurrencyDataCustomPeriod(cryptocurrencyId, fromTs, toTs)
                        .doOnNext {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onNext(it.body()!![0])
                                    emitter.onComplete()
                                } else {
                                    emitter.onError(ServerConnectionError())
                                }
                            }
                        }
                        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
                        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
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

    open fun overviewDataDefinedPeriod(period: String): Observable<RawCoinGraphPointsEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getOverviewDataDefinedPeriod(period)
                        .doOnNext {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onNext(it.body()!![0])
                                    emitter.onComplete()
                                } else {
                                    emitter.onError(ServerConnectionError())
                                }
                            }
                        }
                        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
                        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
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