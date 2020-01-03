package com.coinpaprika.apiclient.repository.global

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class GlobalApi constructor(
    private var retrofit: GlobalApiContract = CoinpaprikaApiFactory()
        .client()
        .create(GlobalApiContract::class.java)
) : GlobalApiContract {

    override fun getGlobal(): Observable<Response<GlobalStatsEntity>> {
        return safeApiCallRaw { retrofit.getGlobal() }
    }
}