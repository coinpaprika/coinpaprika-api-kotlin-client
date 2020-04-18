package com.coinpaprika.apiclient.repository.tag

import com.coinpaprika.apiclient.entity.TagEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TagApiTest {

    @Mock
    private lateinit var mockApi: TagApiContract
    @Mock
    lateinit var mockTag: TagEntity

    @Test
    fun `get tag happy case`() = runBlocking {
        val tag = mockTag
        `when`(mockApi.getTag(FAKE_CRYPTOCURRENCY_ID)).thenReturn(tag)

        val client = TagApi(mockApi)
        assertEquals(tag, client.getTag(FAKE_CRYPTOCURRENCY_ID))
    }

    @Test
    fun `get tags happy case`() = runBlocking{
        val tags = listOf(mockTag)

        `when`(mockApi.getTags()).thenReturn(tags)

        val client = TagApi(mockApi)
        assertEquals(client.getTags(), tags)
    }

    companion object {
        private const val FAKE_CRYPTOCURRENCY_ID = "btc-bitcoin"
    }
}