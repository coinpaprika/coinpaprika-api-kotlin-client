package com.coinpaprika.apiclient.repository.ranking

import com.coinpaprika.apiclient.entity.TopMoversEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RankingApiTest {

    @Mock
    private lateinit var mockApi: RankingApiContract
    @Mock
    lateinit var mockMovers: TopMoversEntity

    @Test
    fun `get movers happy case`() = runBlocking {
        val movers = mockMovers
        val type = "price"

        `when`(mockApi.getTop10Movers(type)).thenReturn(movers)

        val client = RankingApi(mockApi)
        assertEquals(movers, client.getTop10Movers(type))
    }
}