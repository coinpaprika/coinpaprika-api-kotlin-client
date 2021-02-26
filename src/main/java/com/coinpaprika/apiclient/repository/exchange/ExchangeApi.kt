package com.coinpaprika.apiclient.repository.exchange

import com.coinpaprika.apiclient.entity.ExchangeEntity
import com.coinpaprika.apiclient.entity.MarketEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class ExchangeApi(private var contract: ExchangeApiContract) : ExchangeApiContract {

    override fun getExchanges(): Observable<Response<List<ExchangeEntity>>> {
        return safeApiCallRaw { contract.getExchanges() }
    }

    override fun getMarkets(exchangeId: String): Observable<Response<List<MarketEntity>>> {
        return safeApiCallRaw { contract.getMarkets(exchangeId) }
    }
}