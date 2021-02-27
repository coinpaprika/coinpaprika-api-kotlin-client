package com.coinpaprika.apiclient.repository.people

import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class PeopleApi(private var contract: PeopleApiContract) : PeopleApiContract {

    override fun getPerson(id: String): Observable<Response<PersonEntity>> {
        return safeApiCallRaw { contract.getPerson(id) }
    }

    override fun getTweets(id: String): Observable<Response<List<TweetEntity>>> {
        return safeApiCallRaw { contract.getTweets(id) }
    }
}