/*
 * Created by Piotr Kostecki on 09.01.19 12:56
 */

package com.coinpaprika.apiclient.repository.global

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.extensions.handleResponse
import io.reactivex.Observable
import retrofit2.Response

class GlobalApi constructor(
    context: Context,
    private var retrofit: GlobalApiContract = CoinpaprikaApiFactory()
        .client()
        .create(GlobalApiContract::class.java)
) : BaseApi(context), GlobalApiContract {

    override fun getGlobal(): Observable<Response<GlobalStatsEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getGlobal().handleResponse(emitter)
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