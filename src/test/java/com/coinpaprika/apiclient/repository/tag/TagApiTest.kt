package com.coinpaprika.apiclient.repository.tag

import com.coinpaprika.apiclient.entity.TagEntity
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
class TagApiTest {

    @Mock
    private lateinit var mockApi: TagApiContract
    @Mock
    lateinit var mockTag: TagEntity

    @Test
    fun `get tag happy case`() {
        val tag = mockTag
        val response = Response.success(tag)

        `when`(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = TagApi(mockApi)
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

        val client = TagApi(mockApi)
        client.getTags()
            .map { it.body() }
            .test()
            .assertResult(tags)
            .assertComplete()
    }

    @Test
    fun `get tag too many requests error`() {
        val response = tooManyRequestsError<TagEntity>()

        `when`(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = TagApi(mockApi)
        client.getTag(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tags too many requests error`() {
        val response = tooManyRequestsError<List<TagEntity>>()

        `when`(mockApi.getTags())
            .thenReturn(Observable.just(response))

        val client = TagApi(mockApi)
        client.getTags()
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tag server error`() {
        val response = notFoundError<TagEntity>()

        `when`(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = TagApi(mockApi)
        client.getTag(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tags server error`() {
        val response = notFoundError<List<TagEntity>>()

        `when`(mockApi.getTags())
            .thenReturn(Observable.just(response))

        val client = TagApi(mockApi)
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

        val client = TagApi(mockApi)
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

        val client = TagApi(mockApi)
        client.getTags()
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_CRYPTOCURRENCY_ID = "btc-bitcoin"
    }
}