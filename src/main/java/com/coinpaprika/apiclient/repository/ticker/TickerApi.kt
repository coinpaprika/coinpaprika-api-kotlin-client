package com.coinpaprika.apiclient.repository.ticker

import com.coinpaprika.apiclient.entity.TickerEntity
import com.coinpaprika.apiclient.extensions.handleCall

class TickerApi(private val contract: TickerApiContract) {

    suspend fun getTicker(id: String, quotes: List<String>): TickerEntity {
        return handleCall {
            contract.getTicker(id, quotes.joinToString(","))
        }
    }

    suspend fun getTickers(quotes: List<String>): List<TickerEntity> {
        return handleCall {
            contract.getTickers(quotes.joinToString(","))
        }
    }
}