package com.coinpaprika.apiclient.repository

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

fun <T> tooManyRequestsError(): Response<T> {
    return Response.error(
        429,
        "{\"error\":\"too many requests\"}".toResponseBody("application/json".toMediaType())
    )
}

fun <T> notFoundError(): Response<T> {
    return Response.error(
        404,
        "".toResponseBody("application/json".toMediaType())
    )
}