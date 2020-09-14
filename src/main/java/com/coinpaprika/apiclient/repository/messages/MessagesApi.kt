package com.coinpaprika.apiclient.repository.messages

import com.coinpaprika.apiclient.api.CoinpaprikaApiFactory
import com.coinpaprika.apiclient.entity.MessageEntity

class MessagesApi constructor(
    private var retrofit: MessagesApiContract = CoinpaprikaApiFactory()
        .client()
        .create(MessagesApiContract::class.java)
) : MessagesApiContract {

    override suspend fun getMessages(language: String): List<MessageEntity> {
        return retrofit.getMessages(language)
    }
}