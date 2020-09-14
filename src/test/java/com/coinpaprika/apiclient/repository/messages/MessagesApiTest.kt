package com.coinpaprika.apiclient.repository.messages

import com.coinpaprika.apiclient.entity.MessageEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MessagesApiTest {

    @Mock
    private lateinit var mockApi: MessagesApiContract

    @Mock
    lateinit var mockMessages: List<MessageEntity>

    @Test
    fun `get messages happy case`() = runBlocking {
        val language = "pl"

        given(mockApi.getMessages(language)).willReturn(mockMessages)

        val client = MessagesApi(mockApi)
        assertEquals(mockMessages, client.getMessages(language))
    }
}