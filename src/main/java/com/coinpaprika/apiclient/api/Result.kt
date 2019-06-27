/*
 * Created by Piotr Kostecki on 6/27/19 2:19 PM
 */

package com.coinpaprika.apiclient.api

sealed class Result <out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}