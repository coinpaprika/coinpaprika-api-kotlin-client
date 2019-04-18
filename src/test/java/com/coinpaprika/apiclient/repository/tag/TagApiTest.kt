/*
 * Created by Piotr Kostecki on 09.01.19 16:31
 */

package com.coinpaprika.apiclient.repository.tag

import android.content.Context
import com.coinpaprika.apiclient.entity.TagEntity
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
class TagApiTest {

    @Mock private lateinit var mockApi: TagApiContract
    @Mock lateinit var mockTag: TagEntity
    @Mock private lateinit var mockContext: Context

    @Test
    fun `get tag happy case`() {
        val tag = mockTag
        val response = Response.success(tag)

        `when`(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = TagApi(mockContext, mockApi)
        client.getTag(FAKE_CRYPTOCURRENCY_ID)
            .map { it.body() }
            .test()
            .assertResult(tag)
            .assertComplete()
    }

    @Test
    fun `get tags happy case`() {
        val tags = listOf(mockTag)
        val response = Response.success(tags)

        `when`(mockApi.getTags())
            .thenReturn(Observable.just(response))

        val client = TagApi(mockContext, mockApi)
        client.getTags()
            .map { it.body() }
            .test()
            .assertResult(tags)
            .assertComplete()
    }

    @Test
    fun `get tag too many requests error`() {
        val response = Response.error<TagEntity>(429,
            ResponseBody.create(MediaType.parse("application/json"), "\"error\":\"too many requests\")"))

        `when`(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = TagApi(mockContext, mockApi)
        client.getTag(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tags too many requests error`() {
        val response = Response.error<List<TagEntity>>(429,
            ResponseBody.create(MediaType.parse("application/json"), "\"error\":\"too many requests\")"))

        `when`(mockApi.getTags())
            .thenReturn(Observable.just(response))

        val client = TagApi(mockContext, mockApi)
        client.getTags()
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tag server error`() {
        val response = Response.error<TagEntity>(404,
            ResponseBody.create(MediaType.parse("text/html"), ""))

        `when`(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = TagApi(mockContext, mockApi)
        client.getTag(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tags server error`() {
        val response = Response.error<List<TagEntity>>(404,
            ResponseBody.create(MediaType.parse("text/html"), ""))

        `when`(mockApi.getTags())
            .thenReturn(Observable.just(response))

        val client = TagApi(mockContext, mockApi)
        client.getTags()
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tag network connection error`() {
        given(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = TagApi(mockContext, mockApi)
        client.getTag(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tags network connection error`() {
        given(mockApi.getTags())
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = TagApi(mockContext, mockApi)
        client.getTags()
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_CRYPTOCURRENCY_ID = "btc-bitcoin"
    }
}