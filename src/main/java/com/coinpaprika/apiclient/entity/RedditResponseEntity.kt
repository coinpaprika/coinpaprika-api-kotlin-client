/*
 * Created by Piotr Kostecki on 13.12.18 13:14
 */

package com.coinpaprika.apiclient.entity

data class RedditResponseEntity(val data: RedditResponseObject) {
    data class RedditResponseObject(val children: List<RedditEntity>)
}