package com.coinpaprika.apiclient.repository.coin

import com.coinpaprika.apiclient.entity.CoinDetailsEntity
import com.coinpaprika.apiclient.entity.CoinEntity
import com.coinpaprika.apiclient.entity.EventEntity
import com.coinpaprika.apiclient.entity.ExchangeEntity
import com.coinpaprika.apiclient.entity.MarketEntity
import com.coinpaprika.apiclient.entity.TweetEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.repository.notFoundError
import com.coinpaprika.apiclient.repository.tooManyRequestsError
import io.reactivex.Observable
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CoinApiTest {
    @Mock
    private lateinit var mockApi: CoinApiContract
    @Mock
    private lateinit var mockCoin: CoinEntity
    @Mock
    private lateinit var mockDetailsCoin: CoinDetailsEntity
    @Mock
    private lateinit var mockEvent: EventEntity
    @Mock
    private lateinit var mockExchange: ExchangeEntity
    @Mock
    private lateinit var mockMarket: MarketEntity
    @Mock
    private lateinit var mockTweet: TweetEntity

    @Test
    fun `get coin happy case`() = runBlocking {
        val coin = mockDetailsCoin

        `when`(mockApi.getCoin(FAKE_CRYPTOCURRENCY_ID)).thenReturn(coin)

        val client = CoinApi(mockApi)
        assertEquals(coin, client.getCoin(FAKE_CRYPTOCURRENCY_ID))
    }

    @Test
    fun `get coins happy case`() = runBlocking {
        val coins = listOf(mockCoin)

        `when`(mockApi.getCoins()).thenReturn(coins)

        val client = CoinApi(mockApi)

        assertEquals(coins, client.getCoins())
    }

    @Test
    fun `get events happy case`() {
        val events = listOf(mockEvent)
        val response = Response.success(events)

        `when`(mockApi.getEvents(FAKE_CRYPTOCURRENCY_ID)).thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getEvents(FAKE_CRYPTOCURRENCY_ID)
            .map { it.body() }
            .test()
            .assertResult(events)
            .assertComplete()
    }

    @Test
    fun `get exchanges happy case`() {
        val exchanges = listOf(mockExchange)
        val response = Response.success(exchanges)

        `when`(mockApi.getExchanges(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getExchanges(FAKE_CRYPTOCURRENCY_ID)
            .map { it.body() }
            .test()
            .assertResult(exchanges)
            .assertComplete()
    }

    @Test
    fun `get markets happy case`() {
        val markets = listOf(mockMarket)
        val response = Response.success(markets)

        `when`(mockApi.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES)
            .map { it.body() }
            .test()
            .assertResult(markets)
            .assertComplete()
    }

    @Test
    fun `get tweets happy case`() {
        val tweets = listOf(mockTweet)
        val response = Response.success(tweets)

        `when`(mockApi.getTweets(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getTweets(FAKE_CRYPTOCURRENCY_ID)
            .map { it.body() }
            .test()
            .assertResult(tweets)
            .assertComplete()
    }

    @Test
    fun `get events too many requests error`() {
        val response = tooManyRequestsError<List<EventEntity>>()

        `when`(mockApi.getEvents(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getEvents(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError { it is HttpException && it.code() == 429 }
            .assertNotComplete()
    }

    @Test
    fun `get exchanges too many requests error`() {
        val response = tooManyRequestsError<List<ExchangeEntity>>()

        `when`(mockApi.getExchanges(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getExchanges(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError { it is HttpException && it.code() == 429 }
            .assertNotComplete()
    }

    @Test
    fun `get markets too many requests error`() {
        val response = tooManyRequestsError<List<MarketEntity>>()

        `when`(mockApi.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES)
            .test()
            .assertError { it is HttpException && it.code() == 429 }
            .assertNotComplete()
    }

    @Test
    fun `get tweets too many requests error`() {
        val response = tooManyRequestsError<List<TweetEntity>>()

        `when`(mockApi.getTweets(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getTweets(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError { it is HttpException && it.code() == 429 }
            .assertNotComplete()
    }

    @Test
    fun `get events server error`() {
        val response = notFoundError<List<EventEntity>>()

        `when`(mockApi.getEvents(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getEvents(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(HttpException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get exchanges server error`() {
        val response = notFoundError<List<ExchangeEntity>>()

        `when`(mockApi.getExchanges(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getExchanges(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(HttpException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get markets server error`() {
        val response = notFoundError<List<MarketEntity>>()

        `when`(mockApi.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES)
            .test()
            .assertError(HttpException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tweets server error`() {
        val response = notFoundError<List<TweetEntity>>()

        `when`(mockApi.getTweets(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getTweets(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(HttpException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get events network connection error`() {
        given(mockApi.getEvents(FAKE_CRYPTOCURRENCY_ID))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = CoinApi(mockApi)
        client.getEvents(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get exchanges network connection error`() {
        given(mockApi.getExchanges(FAKE_CRYPTOCURRENCY_ID))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = CoinApi(mockApi)
        client.getExchanges(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get markets network connection error`() {
        given(mockApi.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = CoinApi(mockApi)
        client.getMarkets(FAKE_CRYPTOCURRENCY_ID, FAKE_QUOTES)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get tweets network connection error`() {
        given(mockApi.getTweets(FAKE_CRYPTOCURRENCY_ID))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = CoinApi(mockApi)
        client.getTweets(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    companion object {
        private const val FAKE_CRYPTOCURRENCY_ID = "btc-bitcoin"
        private const val FAKE_QUOTES = "btc,eth,usd"
    }
}