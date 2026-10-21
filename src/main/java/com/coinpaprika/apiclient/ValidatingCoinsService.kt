package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.CoinDetailsEntity
import com.coinpaprika.apiclient.entity.CoinEntity
import com.coinpaprika.apiclient.entity.EventEntity
import com.coinpaprika.apiclient.entity.ExchangeEntity
import com.coinpaprika.apiclient.entity.MarketEntity
import com.coinpaprika.apiclient.entity.TweetEntity

/**
 * Wraps a [CoinsService] so every id is validated by [PathId] before the
 * underlying Retrofit call is made.
 */
class ValidatingCoinsService(private val delegate: CoinsService) : CoinsService {
    override suspend fun getCoin(id: String): CoinDetailsEntity {
        PathId.require(id)
        return delegate.getCoin(id)
    }

    override suspend fun getCoins(additionalFields: String?): List<CoinEntity> =
        delegate.getCoins(additionalFields)

    override suspend fun getEvents(id: String): List<EventEntity> {
        PathId.require(id)
        return delegate.getEvents(id)
    }

    override suspend fun getExchanges(id: String): List<ExchangeEntity> {
        PathId.require(id)
        return delegate.getExchanges(id)
    }

    override suspend fun getMarkets(id: String, quotes: String): List<MarketEntity> {
        PathId.require(id)
        return delegate.getMarkets(id, quotes)
    }

    override suspend fun getTweets(id: String): List<TweetEntity> {
        PathId.require(id)
        return delegate.getTweets(id)
    }

    override suspend fun addEvent(cryptoId: String, event: EventEntity) {
        PathId.require(cryptoId)
        delegate.addEvent(cryptoId, event)
    }
}

/** Wraps this [CoinsService] with id validation. */
fun CoinsService.withIdValidation(): CoinsService = ValidatingCoinsService(this)
