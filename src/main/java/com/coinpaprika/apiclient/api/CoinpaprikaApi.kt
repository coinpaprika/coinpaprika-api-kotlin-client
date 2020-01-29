package com.coinpaprika.apiclient.api

import com.coinpaprika.apiclient.entity.*
import com.coinpaprika.apiclient.repository.coin.CoinApi
import com.coinpaprika.apiclient.repository.fiats.FiatApi
import com.coinpaprika.apiclient.repository.global.GlobalApi
import com.coinpaprika.apiclient.repository.news.NewsApi
import com.coinpaprika.apiclient.repository.people.PeopleApi
import com.coinpaprika.apiclient.repository.ranking.RankingApi
import com.coinpaprika.apiclient.repository.search.SearchApi
import com.coinpaprika.apiclient.repository.tag.TagApi
import com.coinpaprika.apiclient.repository.ticker.TickerApi
import io.reactivex.Observable

open class CoinpaprikaApi {

    private var tickerApi = TickerApi()
    private var coinApi = CoinApi()
    private var tagApi = TagApi()
    private var peopleApi = PeopleApi()
    private var searchApi = SearchApi()
    private var newsApi = NewsApi()
    private var rankingApi = RankingApi()
    private var globalApi = GlobalApi()
    private var fiatApi = FiatApi()

    suspend fun ticker(id: String, quotes: String? = null): TickerEntity {
        return tickerApi.getTicker(id, quotes)
    }

    suspend fun tickers(quotes: String? = null): List<TickerEntity> {
        return tickerApi.getTickers(quotes)
    }

    fun coin(id: String): Observable<CoinDetailsEntity> {
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

    fun markets(id: String, quotes: String): Observable<List<MarketEntity>> {
        return coinApi.getMarkets(id, quotes)
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

    fun fiats(): Observable<List<FiatEntity>> {
        return fiatApi.getFiats()
            .map { it.body() }
    }
}