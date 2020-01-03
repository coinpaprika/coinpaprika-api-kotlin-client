package com.coinpaprika.apiclient.repository.ranking

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.TopMoversEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class RankingApi constructor(
    private var retrofit: RankingApiContract = CoinpaprikaApiFactory()
        .client()
        .create(RankingApiContract::class.java)
) : RankingApiContract {

    override fun getTop10Movers(type: String): Observable<Response<TopMoversEntity>> {
        return safeApiCallRaw { retrofit.getTop10Movers(type) }
    }

    override fun getMovers(results: Int, range: String): Observable<Response<TopMoversEntity>> {
        return safeApiCallRaw { retrofit.getMovers(results, range) }
    }
}