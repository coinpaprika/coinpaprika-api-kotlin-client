/*
 * Created by Piotr Kostecki on 09.01.19 12:50
 * kontakt@piotrkostecki.pl
 *
 * Last modified 09.01.19 12:50
 */

package com.coinpaprika.apiclient.repository.ranking

import android.content.Context
import com.coinpaprika.apiclient.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.entity.TopMoversEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import retrofit2.Response

class RankingApi constructor(
    context: Context,
    private var retrofit: RankingApiContract = CoinpaprikaApiFactory(context)
        .client()
        .create(RankingApiContract::class.java)
) : BaseApi(context), RankingApiContract {

    override fun getMovers(): Observable<Response<TopMoversEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getMovers()
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