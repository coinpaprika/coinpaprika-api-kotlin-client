package com.coinpaprika.apiclient.repository.ranking

import com.coinpaprika.apiclient.entity.TopMoversEntity

class RankingApi(private var contract: RankingApiContract) : RankingApiContract {

    override suspend fun getTop10Movers(type: String): TopMoversEntity {
        return contract.getTop10Movers(type)
    }

    override suspend fun getMovers(results: Int, range: String): TopMoversEntity {
        return contract.getMovers(results, range)
    }
}