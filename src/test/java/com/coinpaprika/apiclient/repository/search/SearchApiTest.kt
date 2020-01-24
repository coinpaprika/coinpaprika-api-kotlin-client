package com.coinpaprika.apiclient.repository.search

import com.coinpaprika.apiclient.entity.SearchEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import com.coinpaprika.apiclient.repository.notFoundError
import com.coinpaprika.apiclient.repository.tooManyRequestsError
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class SearchApiTest {

    @Mock
    private lateinit var mockApi: SearchApiContract
    @Mock
    lateinit var mockSearches: SearchEntity

    @Test
    fun `get searches happy case`() {
        val searches = mockSearches
        val response = Response.success(searches)

        `when`(mockApi.getSearches(FAKE_QUERY))
            .thenReturn(Observable.just(response))

        val client = SearchApi(mockApi)
        client.getSearches(FAKE_QUERY)
            .map { it.body() }
            .test()
            .assertResult(searches)
            .assertComplete()
    }

    @Test
    fun `get searches too many requests error`() {
        val response = tooManyRequestsError<SearchEntity>()

        `when`(mockApi.getSearches(FAKE_QUERY))
            .thenReturn(Observable.just(response))

        val client = SearchApi(mockApi)
        client.getSearches(FAKE_QUERY)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get searches server error`() {
        val response = notFoundError<SearchEntity>()

        `when`(mockApi.getSearches(FAKE_QUERY))
            .thenReturn(Observable.just(response))

        val client = SearchApi(mockApi)
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

        val client = SearchApi(mockApi)
        client.getSearches(FAKE_QUERY)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_QUERY = "Bitc"
    }
}