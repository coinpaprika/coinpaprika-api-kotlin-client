package com.coinpaprika.apiclient.entity

import com.google.gson.annotations.SerializedName

enum class CoinType {
    @SerializedName("coin") Coin,
    @SerializedName("token") Token
}