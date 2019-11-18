/*
 * Created by Piotr Kostecki on 09.01.19 16:42
 */

package com.coinpaprika.apiclient.repository.global

import android.content.Context
import com.coinpaprika.apiclient.entity.GlobalStatsEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class NewsApiTest {

    @Mock private lateinit var mockApi: GlobalApiContract
    @Mock lateinit var mockGlobal: GlobalStatsEntity
    @Mock private lateinit var mockContext: Context

    @Test
    fun `get global stats happy case`() {
        val globalStats = mockGlobal
        val response = Response.success(globalStats)

        `when`(mockApi.getGlobal())
            .thenReturn(Observable.just(response))

        val client = GlobalApi(mockContext, mockApi)
        client.getGlobal()
            .map { it.body() }
            .test()
            .assertResult(globalStats)
            .assertComplete()
    }

    @Test
    fun `get global stats too many requests error`() {
        val response = Response.error<GlobalStatsEntity>(429,
            ResponseBody.create("application/json".toMediaType(), "\"error\":\"too many requests\")"))

        `when`(mockApi.getGlobal())
            .thenReturn(Observable.just(response))

        val client = GlobalApi(mockContext, mockApi)
        client.getGlobal()
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get global stats server error`() {
        val response = Response.error<GlobalStatsEntity>(404,
            ResponseBody.create("application/json".toMediaType(), ""))

        `when`(mockApi.getGlobal())
            .thenReturn(Observable.just(response))

        val client = GlobalApi(mockContext, mockApi)
        client.getGlobal()
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get global stats network connection error`() {
        given(mockApi.getGlobal())
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = GlobalApi(mockContext, mockApi)
        client.getGlobal()
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }
}