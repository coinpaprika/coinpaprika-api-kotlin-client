package com.coinpaprika.apiclient

import java.net.URLDecoder

/**
 * Validates an identifier before it is substituted into a Retrofit
 * `@Path` placeholder.
 *
 * Retrofit percent-encodes a `@Path` value and refuses a value that is
 * itself "." or "..", but an id such as "..%2Fkey%2Finfo" or "../key"
 * passes as a single encoded segment. Any server or proxy in front of the
 * API that decodes "%2F" before resolving dot segments turns that into a
 * request to another endpoint, sent with the caller's key. Rejecting such
 * ids here, before the Retrofit call is made, removes that dependency on
 * how every hop parses the path. Non-ASCII is refused too, so Unicode
 * look-alikes of "." and "/" cannot stand in for them; every CoinPaprika
 * id is ASCII.
 */
internal object PathId {
    private val forbiddenChars = charArrayOf('/', '\\', '?', '#', '%')

    fun require(id: String): String {
        val decoded = try {
            URLDecoder.decode(id, "UTF-8")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid id: not valid percent-encoding", e)
        }

        if (decoded.isEmpty() || decoded == "." || decoded == "..") {
            throw IllegalArgumentException("Invalid id: must not be empty, \".\" or \"..\"")
        }

        for (ch in decoded) {
            if (ch.code > 0x7E || ch in forbiddenChars || ch.isWhitespace() || Character.isISOControl(ch)) {
                throw IllegalArgumentException(
                    "Invalid id: must not contain a path separator, a query or fragment delimiter, " +
                        "a percent sign, whitespace, a control character or a non-ASCII character"
                )
            }
        }

        return id
    }
}
