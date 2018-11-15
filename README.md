# Coinpaprika API Android Client

[![Version](https://img.shields.io/badge/Version-0.0.1-blue.svg)](https://coinpaprika.com/pl/)
[![License](https://img.shields.io/cocoapods/l/CoinpaprikaAPI.svg?style=flat)](https://opensource.org/licenses/MIT)
[![Platform](https://img.shields.io/badge/Platform-Android-blue.svg?style=flat)](https://developer.android.com/about/)
![Kotlin 1.2.71](https://img.shields.io/badge/Kotlin-1.2.71-orange.svg)

This library is written in Kotlin and provides the way to retrieve data from [Coinpaprika.com API](https://api.coinpaprika.com/) with ease.

## Installation
1. Add the following lines in your AndroidManifest.xml file
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

2. Edit module's build.gradle file to attach the library by adding this line into it's dependencies
```gradle
implementation 'com.coinpaprika:apiclient:$library_version'
```

## Usage
[Coinpaprika](https://coinpaprika.com) delivers full market data to the world of crypto: coin prices, volumes, market caps, ATHs, return rates and more.

Instantiate client and proceed with the call:
```kotlin
CoinpaprikaAPI(context).tickers()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                for (ticker in it) {
                    i("ExampleActivity", "Ticker name is ${ticker.name} ")
                }
            }
            .doOnComplete { /* nop */ }
            .doOnError { error -> error.printStackTrace() }
            .subscribe()
```

### Tickers

```kotlin
CoinpaprikaAPI(context).tickers()
    .doOnNext {
        for (ticker in it) {
            // ticker.id - Coin identifier, to use in ticker(id:) method
            // ticker.name - Coin name, for example Bitcoin
            // ticker.symbol - Coin symbol, for example BTC
            // ticker.rank - Position in Coinpaprika ranking (by MarketCap)
            // ticker.priceUsd - Price in USD
            // ticker.priceBtc - Price in BTC
            // ticker.dailyVolumeUsd - Volume from last 24h in USD
            // ticker.marketCapUsd - Market Capitalization in USD
            // ticker.circulatingSupply - Circulating Supply
            // ticker.totalSupply - Total Supply
            // ticker.maxSupply - Maximum Supply
            // ticker.percentChange1h - Percentage price change in last 1 hour
            // ticker.percentChange24h - Percentage price change in last 24 hours
            // ticker.percentChange7d - Percentage price change in last 7 days
        }
    }
    .doOnError { error -> /* handle an error */ }
```

## License

CoinpaprikaAPI is available under the MIT license. See the LICENSE file for more info.
