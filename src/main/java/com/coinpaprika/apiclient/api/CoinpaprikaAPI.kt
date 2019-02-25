package com.coinpaprika.apiclient.api

import android.content.Context
import com.coinpaprika.apiclient.entity.*
import com.coinpaprika.apiclient.repository.coin.CoinApi
import com.coinpaprika.apiclient.repository.global.GlobalApi
import com.coinpaprika.apiclient.repository.news.NewsApi
import com.coinpaprika.apiclient.repository.people.PeopleApi
import com.coinpaprika.apiclient.repository.ranking.RankingApi
import com.coinpaprika.apiclient.repository.search.SearchApi
import com.coinpaprika.apiclient.repository.tag.TagApi
import com.coinpaprika.apiclient.repository.ticker.TickerApi
import io.reactivex.Observable

open class CoinpaprikaAPI constructor(context: Context)
    : BaseApi(context) {

    private var tickerApi = TickerApi(context)
    private var coinApi = CoinApi(context)
    private var tagApi = TagApi(context)
    private var peopleApi = PeopleApi(context)
    private var searchApi = SearchApi(context)
    private var newsApi = NewsApi(context)
    private var rankingApi = RankingApi(context)
    private var globalApi = GlobalApi(context)

    fun ticker(id: String): Observable<TickerEntity> {
        return tickerApi.getTicker(id)
            .map { it.body() }
    }

    fun tickers(): Observable<List<TickerEntity>> {
        return tickerApi.getTickers()
            .map { it.body() }
    }

    fun coin(id: String): Observable<CoinEntity> {
        return coinApi.getCoin(id)
            .map { it.body() }
    }

    fun coins(): Observable<List<CoinEntity>> {
        return coinApi.getCoins()
            .map { it.body() }
    }

    fun events(id: String): Observable<List<EventEntity>> {
        return coinApi.getEvents(id)
            .map { it.body() }
    }

    fun exchanges(id: String): Observable<List<ExchangeEntity>> {
        return coinApi.getExchanges(id)
            .map { it.body() }
    }

    fun markets(id: String): Observable<List<MarketEntity>> {
        return coinApi.getMarkets(id)
            .map { it.body() }
    }

    fun tweets(id: String): Observable<List<TweetEntity>> {
        return coinApi.getTweets(id)
            .map { it.body() }
    }

    fun movers(type: String): Observable<TopMoversEntity> {
        return rankingApi.getTop10Movers(type)
            .map { it.body() }
    }

    fun global(): Observable<GlobalStatsEntity> {
        return globalApi.getGlobal()
            .map { it.body() }
    }

    fun tag(id: String): Observable<TagEntity> {
        return tagApi.getTag(id)
            .map { it.body() }
    }

    fun tags(): Observable<List<TagEntity>> {
        return tagApi.getTags()
            .map { it.body() }
    }

    fun person(id: String): Observable<PersonEntity> {
        return peopleApi.getPerson(id)
            .map { it.body() }
    }

    fun search(query: String): Observable<SearchEntity> {
        return searchApi.getSearches(query)
            .map { it.body() }
    }

    fun news(limit: Int): Observable<List<NewsEntity>> {
        return newsApi.getNews(limit)
            .map { it.body() }
    }
}