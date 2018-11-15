package com.coinpaprika.apiclient.exception

/**
 * Exception throw by the application when a there is a network connection exception.
 */
class NetworkConnectionException : Exception {
    constructor() : super()
    constructor(cause: Throwable?) : super(cause)
}