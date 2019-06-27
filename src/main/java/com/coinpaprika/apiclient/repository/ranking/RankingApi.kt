/*
 * Created by Piotr Kostecki on 09.01.19 12:50
 */

package com.coinpaprika.apiclient.repository.ranking

import android.content.Context
import com.coinpaprika.apiclient.api.BaseApi
import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.TopMoversEntity
import com.coinpaprika.apiclient.extensions.safeApiCallRaw
import io.reactivex.Observable
import retrofit2.Response

class RankingApi constructor(
    context: Context,
    private var retrofit: RankingApiContract = CoinpaprikaApiFactory()
        .client()
        .create(RankingApiContract::class.java)
) : BaseApi(context), RankingApiContract {

    override fun getTop10Movers(type: String): Observable<Response<TopMoversEntity>> {
        return safeApiCallRaw { retrofit.getTop10Movers(type) }
    }

    override fun getMovers(results: Int, range: String): Observable<Response<TopMoversEntity>> {
        return safeApiCallRaw { retrofit.getMovers(results, range) }
    }
}