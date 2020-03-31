package com.coinpaprika.apiclient.repository.ranking

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.TopMoversEntity

class RankingApi constructor(
    private var retrofit: RankingApiContract = CoinpaprikaApiFactory()
        .client()
        .create(RankingApiContract::class.java)
) : RankingApiContract {

    override suspend fun getTop10Movers(type: String): TopMoversEntity {
        return retrofit.getTop10Movers(type)
    }

    override suspend fun getMovers(results: Int, range: String): TopMoversEntity {
        return retrofit.getMovers(results, range)
    }
}