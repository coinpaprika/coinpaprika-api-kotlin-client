/*
 * Created by Piotr Kostecki on 13.12.18 13:27
 * kontakt@piotrkostecki.pl
 *
 * Last modified 13.12.18 13:27
 */

package com.coinpaprika.apiclient.api

import android.content.Context
import com.coinpaprika.apiclient.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.RedditResponseEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import io.reactivex.Observable

class RedditAPI constructor(context: Context,
                            private var retrofit: CoinpaprikaRedditApiContract = CoinpaprikaApiFactory(context).reddit())
    : BaseApi(context) {

    open fun getHot(subreddit: String): Observable<RedditResponseEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getHot(subreddit)
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

    open fun getRising(subreddit: String): Observable<RedditResponseEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getRising(subreddit)
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

    open fun getNew(subreddit: String): Observable<RedditResponseEntity> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getNew(subreddit)
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
}