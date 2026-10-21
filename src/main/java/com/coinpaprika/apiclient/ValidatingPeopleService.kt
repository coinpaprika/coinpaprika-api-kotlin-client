package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.PersonEntity
import com.coinpaprika.apiclient.entity.TweetEntity

/**
 * Wraps a [PeopleService] so every id is validated by [PathId] before
 * the underlying Retrofit call is made.
 */
class ValidatingPeopleService(private val delegate: PeopleService) : PeopleService {
    override suspend fun getPerson(id: String): PersonEntity {
        PathId.require(id)
        return delegate.getPerson(id)
    }

    override suspend fun getTweets(id: String): List<TweetEntity> {
        PathId.require(id)
        return delegate.getTweets(id)
    }
}

/** Wraps this [PeopleService] with id validation. */
fun PeopleService.withIdValidation(): PeopleService = ValidatingPeopleService(this)
