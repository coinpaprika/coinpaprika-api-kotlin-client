package com.coinpaprika.apiclient

import android.content.Context
import com.coinpaprika.apiclient.api.CoinpaprikaAPI
import com.coinpaprika.apiclient.api.CoinpaprikaApiContract
import com.coinpaprika.apiclient.entity.TickerEntity
import com.coinpaprika.apiclient.exception.NetworkConnectionException
import com.coinpaprika.apiclient.exception.ServerConnectionError
import com.coinpaprika.apiclient.exception.TooManyRequestsError
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import retrofit2.Response
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CoinpaprikaApiTest {

    @Mock private lateinit var mockApi: CoinpaprikaApiContract
    @Mock private lateinit var mockContext: Context

    @Test
    fun testGetTickersSuccessResult() {
        val tickerEntityList = listOf(
            TickerEntity("1", "Bitcoin", "BTC",
                1, 8000.56, 1.00, 10056234, 123456789,
                12345, 12345, 12345, 12.12,
                12.12, 12.12, "date"))
        val response = Response.success(tickerEntityList)

        `when`(mockApi.getTickers())
            .thenReturn(Observable.just(response))

        val client = CoinpaprikaAPI(mockContext, mockApi)
        client.tickers()
            .test()
            .assertResult(tickerEntityList)
            .assertComplete()
    }

    @Test
    fun testGetTickersTooManyRequestsError() {
        val response = Response.error<List<TickerEntity>>(429,
            ResponseBody.create(MediaType.parse("application/json"), "\"error\":\"too many requests\")"))

        `when`(mockApi.getTickers())
            .thenReturn(Observable.just(response))

        val client = CoinpaprikaAPI(mockContext, mockApi)
        client.tickers()
            .test()
            .assertError(TooManyRequestsError::class.java)
            .assertNotComplete()
    }

    @Test
    fun testGetTickersServerError() {
        val response = Response.error<List<TickerEntity>>(404,
            ResponseBody.create(MediaType.parse("text/html"), ""))

        `when`(mockApi.getTickers())
            .thenReturn(Observable.just(response))

        val client = CoinpaprikaAPI(mockContext, mockApi)
        client.tickers()
            .test()
            .assertError(ServerConnectionError::class.java)
            .assertNotComplete()
    }

    @Test
    fun testGetTickersNetworkConnectionError() {
        `when`(mockApi.getTickers())
            .thenThrow(NetworkConnectionException::class.java)

        val client = CoinpaprikaAPI(mockContext, mockApi)
        client.tickers()
            .test()
            .assertError(NetworkConnectionException::class.java)
            .assertNotComplete()
    }
}