package com.coinpaprika.apiclient.repository.tag

import com.coinpaprika.apiclient.entity.TagEntity

class TagApi(private var contract: TagApiContract) : TagApiContract {

    override suspend fun getTag(id: String): TagEntity {
        return contract.getTag(id)
    }

    override suspend fun getTags(): List<TagEntity> {
        return contract.getTags()
    }
}