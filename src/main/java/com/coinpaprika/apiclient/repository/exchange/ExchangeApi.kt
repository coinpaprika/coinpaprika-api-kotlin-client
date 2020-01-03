package com.coinpaprika.apiclient.repository.exchange

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.ExchangeEntity
import com.coinpaprika.apiclient.entity.MarketEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class ExchangeApi constructor(
    private var retrofit: ExchangeApiContract = CoinpaprikaApiFactory()
        .client()
        .create(ExchangeApiContract::class.java)
) : ExchangeApiContract {

    override fun getExchanges(): Observable<Response<List<ExchangeEntity>>> {
        return safeApiCallRaw { retrofit.getExchanges() }
    }

    override fun getMarkets(exchangeId: String): Observable<Response<List<MarketEntity>>> {
        return safeApiCallRaw { retrofit.getMarkets(exchangeId) }
    }
}