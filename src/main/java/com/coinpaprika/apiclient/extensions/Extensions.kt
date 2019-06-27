/*
 * Created by Piotr Kostecki on 5/17/19 11:48 AM
 */

package com.coinpaprika.apiclient.extensions

import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import retrofit2.Response
import java.lang.Exception


fun <T : Any> BaseApi.safeApiCallRaw(call: () -> Observable<Response<T>>): Observable<Response<T>> {
    return Observable.create {
        if (!this.isThereInternetConnection()) {
            it.onError(NetworkConnectionException())
            return@create
        }
        try {
            call().handleRawResponse(it)
        } catch (e: Exception) {
            it.onError(NetworkConnectionException(e.cause))
        }
    }
}

fun <T> Observable<Response<T>>.handleRawResponse(emitter: ObservableEmitter<Response<T>>): Disposable {
    return this
        .doOnNext {
            if (emitter.isDisposed) return@doOnNext
            if (it.isSuccessful) {
                emitter.onNext(it)
            } else {
                when (it.code()) {
                    429 -> emitter.onError(TooManyRequestsError())
                    else -> emitter.onError(ServerConnectionError())
                }
            }
        }
        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
        .subscribe({}, { error -> error.printStackTrace() })
}