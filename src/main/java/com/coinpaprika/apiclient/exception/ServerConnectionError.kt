package com.coinpaprika.apiclient.exception

class ServerConnectionError : Exception {
    constructor() : super()
    constructor(cause: Throwable) : super(cause)
}