package com.coinpaprika.apiclient.api

import android.content.Context
import com.coinpaprika.apiclient.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.*
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable

open class CoinpaprikaAPI constructor(context: Context,
                                      private var retrofit: CoinpaprikaApiContract = CoinpaprikaApiFactory(context).client())
    : BaseApi(context) {

    open fun ticker(id: String): Observable<TickerEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getTicker(id)
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

    open fun tickers(): Observable<List<TickerEntity>> {
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

    open fun coin(id: String): Observable<CoinEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getCoin(id)
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

    open fun coins(): Observable<List<CoinEntity>> {
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

    open fun tags(): Observable<List<TagEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getTags()
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

    open fun events(id: String): Observable<List<EventEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getEvents(id)
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

    open fun exchanges(id: String): Observable<List<ExchangeEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getExchanges(id)
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

    open fun markets(id: String): Observable<List<MarketEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getMarkets(id)
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

    open fun tweets(id: String): Observable<List<TweetEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getTweets(id)
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

    open fun person(id: String): Observable<PersonEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getPerson(id)
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

    open fun global() : Observable<GlobalStatsEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getGlobal()
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