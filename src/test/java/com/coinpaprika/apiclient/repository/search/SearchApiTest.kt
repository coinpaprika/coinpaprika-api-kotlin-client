package com.coinpaprika.apiclient.repository.search

import com.coinpaprika.apiclient.entity.SearchEntity
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchApiTest {

    @Mock
    private lateinit var mockApi: SearchApiContract
    @Mock
    lateinit var mockSearches: SearchEntity

    @Test
    fun `get searches happy case`() = runBlocking{
        val searches = mockSearches

        `when`(mockApi.getSearches(FAKE_QUERY)).thenReturn(searches)

        val client = SearchApi(mockApi)
        assertEquals(searches, client.getSearches(FAKE_QUERY))
    }

    companion object {
        private const val FAKE_QUERY = "Bitc"
    }
}