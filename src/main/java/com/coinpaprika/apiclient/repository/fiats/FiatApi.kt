package com.coinpaprika.apiclient.repository.fiats

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.FiatEntity

class FiatApi constructor(
    private var retrofit: FiatApiContract = CoinpaprikaApiFactory()
        .client()
        .create(FiatApiContract::class.java)
) : FiatApiContract {

    override suspend fun getFiats(): List<FiatEntity> {
        return retrofit.getFiats()
    }
}