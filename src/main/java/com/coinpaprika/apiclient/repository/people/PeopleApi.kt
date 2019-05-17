/*
 * Created by Piotr Kostecki on 09.01.19 12:46
 */

package com.coinpaprika.apiclient.repository.people

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.extensions.handleResponse
import io.reactivex.Observable
import retrofit2.Response

class PeopleApi constructor(
    context: Context,
    private var retrofit: PeopleApiContract = CoinpaprikaApiFactory()
        .client()
        .create(PeopleApiContract::class.java)
) : BaseApi(context), PeopleApiContract {

    override fun getPerson(id: String): Observable<Response<PersonEntity>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getPerson(id).handleResponse(emitter)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            } else {
                emitter.onError(NetworkConnectionException())
            }
        }
    }

    override fun getTweets(id: String): Observable<Response<List<TweetEntity>>> {
        return Observable.create { emitter ->
            if (isThereInternetConnection()) {
                try {
                    retrofit.getTweets(id).handleResponse(emitter)
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