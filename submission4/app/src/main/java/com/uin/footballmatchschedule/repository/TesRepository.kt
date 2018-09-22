package com.uin.footballmatchschedule.repository

import com.uin.footballmatchschedule.model.TestResult
import com.uin.footballmatchschedule.rest.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TesRepository {

    fun getNextMatch(id: String, callback: TesRepositoryCallback<TestResult?>) {
        Service
                .create()
                .getNextMatchTest(id)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    data?.let {
                        callback.onDataLoaded(it)
                    } ?: run {
                        callback.onDataError()
                    }
                }, {
                    callback.onDataError()
                })
    }
}