/*
 * Created by Piotr Kostecki on 5/17/19 11:48 AM
 */

package com.coinpaprika.apiclient.extensions

import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import retrofit2.Response

fun <T> Observable<Response<T>>.handleResponse(emitter: ObservableEmitter<Response<T>>): Disposable {
    return this.doOnNext {
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
}