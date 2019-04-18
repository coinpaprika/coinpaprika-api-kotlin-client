/*
 * Created by Piotr Kostecki on 09.01.19 16:48
 */

package com.coinpaprika.apiclient.repository.search

import android.content.Context
import com.coinpaprika.apiclient.entity.SearchEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class SearchApiTest {

    @Mock private lateinit var mockApi: SearchApiContract
    @Mock lateinit var mockSearches: SearchEntity
    @Mock private lateinit var mockContext: Context

    @Test
    fun `get searches happy case`() {
        val searches = mockSearches
        val response = Response.success(searches)

        `when`(mockApi.getSearches(FAKE_QUERY))
            .thenReturn(Observable.just(response))

        val client = SearchApi(mockContext, mockApi)
        client.getSearches(FAKE_QUERY)
            .map { it.body() }
            .test()
            .assertResult(searches)
            .assertComplete()
    }

    @Test
    fun `get searches too many requests error`() {
        val response = Response.error<SearchEntity>(429,
            ResponseBody.create(MediaType.parse("application/json"), "\"error\":\"too many requests\")"))

        `when`(mockApi.getSearches(FAKE_QUERY))
            .thenReturn(Observable.just(response))

        val client = SearchApi(mockContext, mockApi)
        client.getSearches(FAKE_QUERY)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get searches server error`() {
        val response = Response.error<SearchEntity>(404,
            ResponseBody.create(MediaType.parse("text/html"), ""))

        `when`(mockApi.getSearches(FAKE_QUERY))
            .thenReturn(Observable.just(response))

        val client = SearchApi(mockContext, mockApi)
        client.getSearches(FAKE_QUERY)
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get searches network connection error`() {
        given(mockApi.getSearches(FAKE_QUERY))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = SearchApi(mockContext, mockApi)
        client.getSearches(FAKE_QUERY)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_QUERY = "Bitc"
    }
}