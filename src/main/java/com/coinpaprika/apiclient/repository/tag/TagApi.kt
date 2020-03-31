package com.coinpaprika.apiclient.repository.tag

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.TagEntity

class TagApi constructor(
    private var retrofit: TagApiContract = CoinpaprikaApiFactory()
        .client()
        .create(TagApiContract::class.java)
) : TagApiContract {

    override suspend fun getTag(id: String): TagEntity {
        return retrofit.getTag(id)
    }

    override suspend fun getTags(): List<TagEntity> {
        return retrofit.getTags()
    }
}