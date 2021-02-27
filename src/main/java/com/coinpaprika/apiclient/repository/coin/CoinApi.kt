package com.coinpaprika.apiclient.repository.coin

import com.coinpaprika.apiclient.entity.*
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class CoinApi(private var contract: CoinApiContract) : CoinApiContract {

    override suspend fun getCoin(id: String): CoinDetailsEntity {
        return contract.getCoin(id)
    }

    override suspend fun getCoins(additionalFields: String?): List<CoinEntity> {
        return contract.getCoins(additionalFields)
    }

    override fun getEvents(id: String): Observable<Response<List<EventEntity>>> {
        return safeApiCallRaw { contract.getEvents(id) }
    }

    override fun getExchanges(id: String): Observable<Response<List<ExchangeEntity>>> {
        return safeApiCallRaw { contract.getExchanges(id) }
    }

    override fun getMarkets(id: String, quotes: String): Observable<Response<List<MarketEntity>>> {
        return safeApiCallRaw { contract.getMarkets(id, quotes) }
    }

    override fun getTweets(id: String): Observable<Response<List<TweetEntity>>> {
        return safeApiCallRaw { contract.getTweets(id) }
    }

    override fun addEvent(cryptoId: String, event: EventEntity): Observable<Response<Void>> {
        return safeApiCallRaw { contract.addEvent(cryptoId, event) }
    }
}