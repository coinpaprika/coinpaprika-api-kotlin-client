package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

data class MessageEntity(
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("platform_id") val platformId: String?,
    @SerializedName("label") val label: String,
    @SerializedName("scope") val scope: String,
    @SerializedName("message") val message: String,
    @SerializedName("actions") val actions: List<String>
) {

    companion object {
        const val LABEL_IMPORTANT = "important"
        const val LABEL_WARNING = "warning"
        const val LABEL_NOTICE = "notice"

        const val SCOPE_COIN_ONLY = "coin_only"
        const val SCOPE_WITH_TOKENS = "with_tokens"
        const val SCOPE_TOKENS_ONLY = "tokens_only"

        const val ACTION_DISABLE_SEND = "disable_send"
        const val ACTION_DISABLE_SELL = "disable_sell"
        const val ACTION_DISABLE_BUY = "disable_buy"
        const val ACTION_DISABLE_EXCHANGE = "disable_exchange"
    }
}