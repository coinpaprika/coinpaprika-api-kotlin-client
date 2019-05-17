/*
 * Created by Piotr Kostecki on 09.01.19 12:26
 */

package com.coinpaprika.apiclient.repository.ticker

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.TickerEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.extensions.handleResponse
import io.reactivex.Observable
import retrofit2.Response

class TickerApi constructor(
    context: Context,
    private var retrofit: TickerApiContract = CoinpaprikaApiFactory()
        .client()
        .create(TickerApiContract::class.java)
) : BaseApi(context), TickerApiContract {

    override fun getTicker(id: String, quotes: String): Observable<Response<TickerEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getTicker(id).handleResponse(emitter)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    override fun getTickers(quotes: String): Observable<Response<List<TickerEntity>>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getTickers(quotes).handleResponse(emitter)
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