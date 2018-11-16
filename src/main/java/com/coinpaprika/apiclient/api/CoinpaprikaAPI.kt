package com.coinpaprika.apiclient.api

import android.content.Context
import com.coinpaprika.apiclient.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.CoinEntity
import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import com.coinpaprika.apiclient.entity.TickerEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable

open class CoinpaprikaAPI constructor(context: Context,
                                      private var retrofit: CoinpaprikaApiContract = CoinpaprikaApiFactory.client()) :
    BaseApi(context) {

    fun tickers(): Observable<List<TickerEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getTickers()
                        .doOnNext {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onNext(it.body()!!)
                                    emitter.onComplete()
                                } else {
                                    when (it.code()) {
                                        429 -> emitter.onError(TooManyRequestsError())
                                        else -> emitter.onError(ServerConnectionError())
                                    }
                                }
                            }
                        }
                        .subscribe({_ -> }, {error -> error.printStackTrace()})
                } catch (e: Exception) {
                    e.printStackTrace()
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    fun coins(): Observable<List<CoinEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getCoins()
                        .doOnNext {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onNext(it.body()!!)
                                } else {
                                    when (it.code()) {
                                        429 -> emitter.onError(TooManyRequestsError())
                                        else -> emitter.onError(ServerConnectionError())
                                    }
                                }
                            }
                        }
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