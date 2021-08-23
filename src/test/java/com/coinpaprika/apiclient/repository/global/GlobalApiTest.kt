package com.coinpaprika.apiclient.repository.global

import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.repository.notFoundError
import com.coinpaprika.apiclient.repository.tooManyRequestsError
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class NewsApiTest {

    @Mock private lateinit var mockApi: GlobalApiContract
    @Mock lateinit var mockGlobal: GlobalStatsEntity

    @Test
    fun `get global stats happy case`() {
        val globalStats = mockGlobal
        val response = Response.success(globalStats)

        `when`(mockApi.getGlobal())
            .thenReturn(Observable.just(response))

        val client = GlobalApi(mockApi)
        client.getGlobal()
            .map { it.body() }
            .test()
            .assertResult(globalStats)
            .assertComplete()
    }

    @Test
    fun `get global stats too many requests error`() {
        val response = tooManyRequestsError<GlobalStatsEntity>()

        `when`(mockApi.getGlobal())
            .thenReturn(Observable.just(response))

        val client = GlobalApi(mockApi)
        client.getGlobal()
            .test()
            .assertError { it is HttpException && it.code() == 429 }
            .assertNotComplete()
    }

    @Test
    fun `get global stats server error`() {
        val response = notFoundError<GlobalStatsEntity>()

        `when`(mockApi.getGlobal())
            .thenReturn(Observable.just(response))

        val client = GlobalApi(mockApi)
        client.getGlobal()
            .test()
            .assertError(HttpException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get global stats network connection error`() {
        given(mockApi.getGlobal())
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = GlobalApi(mockApi)
        client.getGlobal()
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }
}