/*
 * Created by Piotr Kostecki on 01.02.19 15:46
 */

package com.coinpaprika.apiclient.repository.exchange

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.ExchangeEntity
import com.coinpaprika.apiclient.entity.MarketEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.extensions.handleResponse
import io.reactivex.Observable
import retrofit2.Response

class ExchangeApi constructor(
    context: Context,
    private var retrofit: ExchangeApiContract = CoinpaprikaApiFactory()
        .client()
        .create(ExchangeApiContract::class.java)
) : BaseApi(context), ExchangeApiContract {

    override fun getExchanges(): Observable<Response<List<ExchangeEntity>>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getExchanges().handleResponse(emitter)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    override fun getMarkets(exchangeId: String): Observable<Response<List<MarketEntity>>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getMarkets(exchangeId).handleResponse(emitter)
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