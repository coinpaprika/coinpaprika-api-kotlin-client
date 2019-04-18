/*
 * Created by Piotr Kostecki on 23.11.18 16:27
 */

package com.coinpaprika.apiclient

enum class GraphPeriods(val period: String) {
    DAILY("24h"),
    WEEKLY("7d"),
    MONTHLY("30d"),
    QUARTERLY("1q"),
    YEARLY("1y"),
    YTD("ytd"),
    MAX("all"),
    CUSTOM("custom"),
}