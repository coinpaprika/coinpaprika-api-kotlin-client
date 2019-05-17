/*
 * Created by Piotr Kostecki on 4/1/19 1:34 PM PM
 */

package com.coinpaprika.apiclient.repository.fiats

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.FiatEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.extensions.handleResponse
import io.reactivex.Observable
import retrofit2.Response

class FiatApi constructor(
    context: Context,
    private var retrofit: FiatApiContract = CoinpaprikaApiFactory()
        .client()
        .create(FiatApiContract::class.java)
) : BaseApi(context), FiatApiContract {
    override fun getFiats(): Observable<Response<List<FiatEntity>>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getFiats().handleResponse(emitter)
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