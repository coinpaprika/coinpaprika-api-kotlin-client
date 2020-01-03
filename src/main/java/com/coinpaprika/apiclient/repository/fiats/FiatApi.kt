package com.coinpaprika.apiclient.repository.fiats

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.FiatEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class FiatApi constructor(
    private var retrofit: FiatApiContract = CoinpaprikaApiFactory()
        .client()
        .create(FiatApiContract::class.java)
) : FiatApiContract {

    override fun getFiats(): Observable<Response<List<FiatEntity>>> {
        return safeApiCallRaw { retrofit.getFiats() }
    }
}