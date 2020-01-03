package com.coinpaprika.apiclient.repository.ticker

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.TickerEntity
import com.coinpaprika.apiclient.extensions.handleCall

class TickerApi {

    private var retrofit: TickerApiContract = CoinpaprikaApiFactory()
        .client()
        .create(TickerApiContract::class.java)

    suspend fun getTicker(id: String, quotes: String? = null): TickerEntity {
        return handleCall {
            if (quotes != null) {
                retrofit.getTicker(id, quotes)
            } else {
                retrofit.getTicker(id)
            }
        }
    }

    suspend fun getTickers(quotes: String? = null): List<TickerEntity> {
        return handleCall {
            if (quotes != null) {
                retrofit.getTickers(quotes)
            } else {
                retrofit.getTickers()
            }
        }
    }
}