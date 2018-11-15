package com.coinpaprika.example

import android.app.Activity
import android.os.Bundle
import android.util.Log.i
import com.coinpaprika.apiclient.api.CoinpaprikaAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExampleActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.makeCallToAPI()
    }

    private fun makeCallToAPI() {
        CoinpaprikaAPI(this).tickers()
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
    }

}