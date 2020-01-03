package com.coinpaprika.apiclient.repository.tag

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.TagEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class TagApi constructor(
    private var retrofit: TagApiContract = CoinpaprikaApiFactory()
        .client()
        .create(TagApiContract::class.java)
) : TagApiContract {

    override fun getTag(id: String): Observable<Response<TagEntity>> {
        return safeApiCallRaw { retrofit.getTag(id) }
    }

    override fun getTags(): Observable<Response<List<TagEntity>>> {
        return safeApiCallRaw { retrofit.getTags() }
    }
}