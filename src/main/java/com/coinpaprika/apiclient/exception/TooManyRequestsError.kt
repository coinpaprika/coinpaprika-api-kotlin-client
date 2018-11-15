package com.coinpaprika.apiclient.exception

/**
 * Exception throw by the application when application is using API massively
 */
class TooManyRequestsError : Exception {
    constructor() : super()
    constructor(cause: Throwable?) : super(cause)
}