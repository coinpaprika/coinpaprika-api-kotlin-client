/*
 * Created by Piotr Kostecki on 09.01.19 12:46
 */

package com.coinpaprika.apiclient.repository.people

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class PeopleApi constructor(
    context: Context,
    private var retrofit: PeopleApiContract = CoinpaprikaApiFactory()
        .client()
        .create(PeopleApiContract::class.java)
) : BaseApi(context), PeopleApiContract {

    override fun getPerson(id: String): Observable<Response<PersonEntity>> {
        return safeApiCallRaw { retrofit.getPerson(id) }
    }

    override fun getTweets(id: String): Observable<Response<List<TweetEntity>>> {
        return safeApiCallRaw { retrofit.getTweets(id) }
    }
}