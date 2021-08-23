package com.coinpaprika.apiclient.extensions

import com.coinpaprika.apiclient.exception.NetworkConnectionException
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, call: suspend () -> Response<T>): T =
    withContext(dispatcher) {
        val response = try {
            call()
        } catch (e: CancellationException) {
            throw e
        } catch (t: Throwable) {
            throw NetworkConnectionException(t.cause)
        }
        if (response.isSuccessful) {
            response.body()!!
        } else {
            throw HttpException(response)
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
    return doOnNext { response ->
        if (emitter.isDisposed) return@doOnNext
        if (response.isSuccessful) {
            emitter.onNext(response)
        } else {
            emitter.onError(HttpException(response))
        }
    }
        .onErrorResumeNext { it: Throwable -> Observable.error(NetworkConnectionException(it.cause)) }
        .doOnComplete { if (!emitter.isDisposed) emitter.onComplete() }
        .doOnError { if (!emitter.isDisposed) emitter.onError(it) }
        .subscribe({}, { error -> error.printStackTrace() })
}