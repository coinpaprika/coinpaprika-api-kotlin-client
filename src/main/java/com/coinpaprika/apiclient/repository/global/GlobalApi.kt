package com.coinpaprika.apiclient.repository.global

import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class GlobalApi(private var contract: GlobalApiContract) : GlobalApiContract {

    override fun getGlobal(): Observable<Response<GlobalStatsEntity>> {
        return safeApiCallRaw { contract.getGlobal() }
    }
}