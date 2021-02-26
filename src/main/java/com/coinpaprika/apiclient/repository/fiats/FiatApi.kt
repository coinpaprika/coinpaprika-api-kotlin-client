package com.coinpaprika.apiclient.repository.fiats

import com.coinpaprika.apiclient.entity.FiatEntity

class FiatApi(private var contract: FiatApiContract) : FiatApiContract {

    override suspend fun getFiats(): List<FiatEntity> {
        return contract.getFiats()
    }
}