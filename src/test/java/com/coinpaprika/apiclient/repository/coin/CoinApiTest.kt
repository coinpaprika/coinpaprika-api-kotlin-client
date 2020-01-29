package com.coinpaprika.apiclient.repository.coin

import com.coinpaprika.apiclient.entity.*
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
    fun `get coin happy case`() {
        val coin = mockDetailsCoin
        val response = Response.success(coin)

        `when`(mockApi.getCoin(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getCoin(FAKE_CRYPTOCURRENCY_ID)
            .map { it.body() }
            .test()
            .assertResult(coin)
            .assertComplete()
    }

    @Test
    fun `get coins happy case`() {
        val coins = listOf(mockCoin)
        val response = Response.success(coins)

        `when`(mockApi.getCoins())
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getCoins()
            .map { it.body() }
            .test()
            .assertResult(coins)
            .assertComplete()
    }

    @Test
    fun `get events happy case`() {
        val events = listOf(mockEvent)
        val response = Response.success(events)

        `when`(mockApi.getEvents(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

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
    fun `get coin too many requests error`() {
        val response = tooManyRequestsError<CoinDetailsEntity>()

        `when`(mockApi.getCoin(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getCoin(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get coins too many requests error`() {
        val response = tooManyRequestsError<List<CoinEntity>>()

        `when`(mockApi.getCoins())
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getCoins()
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get events too many requests error`() {
        val response = tooManyRequestsError<List<EventEntity>>()

        `when`(mockApi.getEvents(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getEvents(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(TooManyRequestsError::class.java)
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
            .assertError(TooManyRequestsError::class.java)
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
            .assertError(TooManyRequestsError::class.java)
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
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get coin server error`() {
        val response = notFoundError<CoinDetailsEntity>()

        `when`(mockApi.getCoin(FAKE_CRYPTOCURRENCY_ID))
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getCoin(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get coins server error`() {
        val response = notFoundError<List<CoinEntity>>()

        `when`(mockApi.getCoins())
            .thenReturn(Observable.just(response))

        val client = CoinApi(mockApi)
        client.getCoins()
            .test()
            .assertError(ServerConnectionError::class.java)
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
            .assertError(ServerConnectionError::class.java)
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
            .assertError(ServerConnectionError::class.java)
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
            .assertError(ServerConnectionError::class.java)
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
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get coin network connection error`() {
        given(mockApi.getCoin(FAKE_CRYPTOCURRENCY_ID))
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = CoinApi(mockApi)
        client.getCoin(FAKE_CRYPTOCURRENCY_ID)
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }

    @Test
    fun `get coins network connection error`() {
        given(mockApi.getCoins())
            .willAnswer {
                throw NetworkConnectionException()
            }

        val client = CoinApi(mockApi)
        client.getCoins()
            .test()
            .assertError(NetworkConnectionException::class.java)
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