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
import retrofit2.Retrofit
import retrofit2.create

open class CoinpaprikaApi(retrofit: Retrofit) {

    private var tickerApi = TickerApi(retrofit.create())
    private var coinApi = CoinApi(retrofit.create())
    private var tagApi = TagApi(retrofit.create())
    private var peopleApi = PeopleApi(retrofit.create())
    private var searchApi = SearchApi(retrofit.create())
    private var newsApi = NewsApi(retrofit.create())
    private var rankingApi = RankingApi(retrofit.create())
    private var globalApi = GlobalApi(retrofit.create())
    private var fiatApi = FiatApi(retrofit.create())

    suspend fun ticker(id: String, quotes: String? = null): TickerEntity {
        return tickerApi.getTicker(id, quotes)
    }

    suspend fun tickers(quotes: String? = null): List<TickerEntity> {
        return tickerApi.getTickers(quotes)
    }

    suspend fun coin(id: String):CoinDetailsEntity {
        return coinApi.getCoin(id)
    }

    suspend fun coins(): List<CoinEntity> {
        return coinApi.getCoins()
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

    suspend fun movers(type: String): TopMoversEntity {
        return rankingApi.getTop10Movers(type)
    }

    fun global(): Observable<GlobalStatsEntity> {
        return globalApi.getGlobal()
            .map { it.body() }
    }

    suspend fun tag(id: String): TagEntity {
        return tagApi.getTag(id)
    }

    suspend fun tags(): List<TagEntity> {
        return tagApi.getTags()
    }

    fun person(id: String): Observable<PersonEntity> {
        return peopleApi.getPerson(id)
            .map { it.body() }
    }

    suspend fun search(query: String): SearchEntity {
        return searchApi.getSearches(query)
    }

    fun news(limit: Int): Observable<List<NewsEntity>> {
        return newsApi.getNews(limit)
            .map { it.body() }
    }

    suspend fun fiats(): List<FiatEntity> {
        return fiatApi.getFiats()
    }
}