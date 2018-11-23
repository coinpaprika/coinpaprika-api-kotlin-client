/*
 * Created by Piotr Kostecki on 23.11.18 16:27
 * kontakt@piotrkostecki.pl
 *
 * Last modified 23.11.18 16:27
 */

package com.coinpaprika.apiclient

enum class GraphPeriods(val period: String) {
    DAILY("24h"),
    WEEKLY("7d"),
    MONTHLY("30d"),
    YEARLY("1y")
}