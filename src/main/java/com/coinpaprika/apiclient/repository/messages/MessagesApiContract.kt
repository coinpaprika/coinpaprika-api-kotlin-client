package com.coinpaprika.apiclient.repository.messages

import com.coinpaprika.apiclient.entity.MessageEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface MessagesApiContract {
    @GET("internal/messages")
    suspend fun getMessages(@Query("lang") language: String): List<MessageEntity>
}