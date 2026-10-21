package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.TagEntity

/**
 * Wraps a [TagsService] so every id is validated by [PathId] before the
 * underlying Retrofit call is made.
 */
class ValidatingTagsService(private val delegate: TagsService) : TagsService {
    override suspend fun getTag(id: String): TagEntity {
        PathId.require(id)
        return delegate.getTag(id)
    }

    override suspend fun getTags(): List<TagEntity> = delegate.getTags()
}

/** Wraps this [TagsService] with id validation. */
fun TagsService.withIdValidation(): TagsService = ValidatingTagsService(this)
