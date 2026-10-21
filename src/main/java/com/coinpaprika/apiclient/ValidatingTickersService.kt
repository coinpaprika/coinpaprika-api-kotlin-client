package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.TickerEntity

/**
 * Wraps a [TickersService] so every id is validated by [PathId] before
 * the underlying Retrofit call is made.
 */
class ValidatingTickersService(private val delegate: TickersService) : TickersService {
    override suspend fun getTicker(
        id: String,
        quotes: String,
        options: Map<String, String>
    ): TickerEntity {
        PathId.require(id)
        return delegate.getTicker(id, quotes, options)
    }

    override suspend fun getTickers(
        quotes: String,
        page: Int?,
        options: Map<String, String>
    ): List<TickerEntity> = delegate.getTickers(quotes, page, options)
}

/** Wraps this [TickersService] with id validation. */
fun TickersService.withIdValidation(): TickersService = ValidatingTickersService(this)
