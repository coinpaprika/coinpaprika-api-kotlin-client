/*
 * Created by Piotr Kostecki on 04.12.18 13:04
 * kontakt@piotrkostecki.pl
 *
 * Last modified 04.12.18 13:04
 */

package com.coinpaprika.apiclient.request

class CoinLogoRequest(val id: String, val size: Sizes) {

    enum class Sizes(var size: String) {
        NORMAL("logo"),
        THUMB("logo-thumb")
    }

    var url: String = "https://coinpaprika.com/coin/$id/${size.size}.png"
}