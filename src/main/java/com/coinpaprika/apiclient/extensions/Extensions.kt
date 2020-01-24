/*
 * Created by Piotr Kostecki on 5/17/19 11:48 AM
 */

package com.coinpaprika.apiclient.extensions

import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import retrofit2.Response

suspend fun <T : Any> handleCall(call: suspend () -> Response<T>): T {
    val response = try {
        call()
    } catch (t: Throwable) {
        throw NetworkConnectionException(t.cause)
    }
    if (response.isSuccessful) {
        return response.body()!!
    } else when (response.code()) {
        429  -> throw TooManyRequestsError()
        else -> throw ServerConnectionError()
    }
}

internal fun <T : Any> safeApiCallRaw(call: () -> Observable<Response<T>>): Observable<Response<T>> {
    return Observable.create {
        try {
            call().handleRawResponse(it)
        } catch (e: Exception) {
            it.onError(NetworkConnectionException(e.cause))
        }
    }
}

internal fun <T> Observable<Response<T>>.handleRawResponse(emitter: ObservableEmitter<Response<T>>): Disposable {
    return this
        .doOnNext {
            if (emitter.isDisposed) return@doOnNext
            if (it.isSuccessful) {
                emitter.onNext(it)
            } else {
                when (it.code()) {
                    429  -> emitter.onError(TooManyRequestsError())
                    else -> emitter.onError(ServerConnectionError())
                }
            }
        }
        .onErrorResumeNext { it: Throwable -> Observable.error(NetworkConnectionException(it.cause)) }
        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
        .subscribe({}, { error -> error.printStackTrace() })
}