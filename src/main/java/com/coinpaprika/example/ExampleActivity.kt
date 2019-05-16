package com.coinpaprika.example

import android.app.Activity
import android.os.Bundle
import android.util.Log.i
import com.coinpaprika.apiclient.api.CoinpaprikaApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ExampleActivity: Activity() {

    private var compositeDisposable= CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.makeCallToAPI()
    }

    private fun makeCallToAPI() {
        // Coinpaprika API call
        compositeDisposable.add(
            CoinpaprikaApi(this)
                .tickers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { error -> error.printStackTrace() }
                .subscribe(
                    { next -> for (ticker in next) {
                        i("ExampleActivity", "Ticker name is ${ticker.name} ")
                    }},
                    { error -> error.printStackTrace() }
                )
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}