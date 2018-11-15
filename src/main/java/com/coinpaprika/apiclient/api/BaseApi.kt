package com.coinpaprika.apiclient.api

import android.content.Context
import android.net.ConnectivityManager

abstract class BaseApi(private val context: Context) {
    fun isThereInternetConnection(): Boolean {
        val isConnected: Boolean

        val connectivityManager = this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting

        return isConnected
    }
}