package com.coinpaprika.apiclient.repository.news

import com.coinpaprika.apiclient.entity.NewsEntity
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
class NewsApiTest {

    @Mock private lateinit var mockApi: NewsApiContract
    @Mock lateinit var mockNews: NewsEntity

    @Test
    fun `get news happy case`() {
        val news = listOf(mockNews)
        val response = Response.success(news)

        `when`(mockApi.getNews(FAKE_LIMIT))
            .thenReturn(Observable.just(response))

        val client = NewsApi(mockApi)
        client.getNews(FAKE_LIMIT)
            .map { it.body() }
            .test()
            .assertResult(news)
            .assertComplete()
    }

    @Test
    fun `get news too many requests error`() {
        val response = tooManyRequestsError<List<NewsEntity>>()

        `when`(mockApi.getNews(FAKE_LIMIT))
            .thenReturn(Observable.just(response))

        val client = NewsApi(mockApi)
        client.getNews(FAKE_LIMIT)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get news server error`() {
        val response = notFoundError<List<NewsEntity>>()

        `when`(mockApi.getNews(FAKE_LIMIT))
            .thenReturn(Observable.just(response))

        val client = NewsApi(mockApi)
        client.getNews(FAKE_LIMIT)
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get news network connection error`() {
        given(mockApi.getNews(FAKE_LIMIT))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = NewsApi(mockApi)
        client.getNews(FAKE_LIMIT)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_LIMIT = 6
    }
}