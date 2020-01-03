package com.coinpaprika.apiclient.repository.people

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class PeopleApi constructor(
    private var retrofit: PeopleApiContract = CoinpaprikaApiFactory()
        .client()
        .create(PeopleApiContract::class.java)
) : PeopleApiContract {

    override fun getPerson(id: String): Observable<Response<PersonEntity>> {
        return safeApiCallRaw { retrofit.getPerson(id) }
    }

    override fun getTweets(id: String): Observable<Response<List<TweetEntity>>> {
        return safeApiCallRaw { retrofit.getTweets(id) }
    }
}