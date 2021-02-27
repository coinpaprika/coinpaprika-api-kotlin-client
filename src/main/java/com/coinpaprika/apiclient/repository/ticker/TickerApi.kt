package com.coinpaprika.apiclient.repository.ticker

import com.coinpaprika.apiclient.entity.TickerEntity
import com.coinpaprika.apiclient.extensions.handleCall

class TickerApi(private val contract: TickerApiContract) {

    suspend fun getTicker(id: String, quotes: String? = null): TickerEntity {
        return handleCall {
            if (quotes != null) {
                contract.getTicker(id, quotes)
            } else {
                contract.getTicker(id)
            }
        }
    }

    suspend fun getTickers(quotes: String? = null): List<TickerEntity> {
        return handleCall {
            if (quotes != null) {
                contract.getTickers(quotes)
            } else {
                contract.getTickers()
            }
        }
    }
}