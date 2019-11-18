/*
 * Created by Piotr Kostecki on 09.01.19 16:24
 */

package com.coinpaprika.apiclient.repository.news

import android.content.Context
import com.coinpaprika.apiclient.entity.NewsEntity
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

    @Mock private lateinit var mockApi: NewsApiContract
    @Mock lateinit var mockNews: NewsEntity
    @Mock private lateinit var mockContext: Context

    @Test
    fun `get news happy case`() {
        val news = listOf(mockNews)
        val response = Response.success(news)

        `when`(mockApi.getNews(FAKE_LIMIT))
            .thenReturn(Observable.just(response))

        val client = NewsApi(mockContext, mockApi)
        client.getNews(FAKE_LIMIT)
            .map { it.body() }
            .test()
            .assertResult(news)
            .assertComplete()
    }

    @Test
    fun `get news too many requests error`() {
        val response = Response.error<List<NewsEntity>>(429,
            ResponseBody.create("application/json".toMediaType(), "\"error\":\"too many requests\")"))

        `when`(mockApi.getNews(FAKE_LIMIT))
            .thenReturn(Observable.just(response))

        val client = NewsApi(mockContext, mockApi)
        client.getNews(FAKE_LIMIT)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get news server error`() {
        val response = Response.error<List<NewsEntity>>(404,
            ResponseBody.create("application/json".toMediaType(), ""))

        `when`(mockApi.getNews(FAKE_LIMIT))
            .thenReturn(Observable.just(response))

        val client = NewsApi(mockContext, mockApi)
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

        val client = NewsApi(mockContext, mockApi)
        client.getNews(FAKE_LIMIT)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_LIMIT = 6
    }
}