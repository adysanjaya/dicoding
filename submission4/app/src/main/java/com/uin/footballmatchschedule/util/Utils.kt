package com.uin.footballmatchschedule.util

import android.annotation.SuppressLint
import com.uin.footballmatchschedule.rest.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    @SuppressLint("SimpleDateFormat")
    fun toSimpleString(date: Date?): String? = with(date ?: Date()) {
        SimpleDateFormat("EEE, dd MM yyy").format(this)
    }

    private var disposable: Disposable? = null

    private val service by lazy {
        Service.create()
    }

    private fun beginSearch() {
        disposable = service.getNextMatch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
//                        { result -> showResult(result) },
//                        { error -> aksiError(error.message) }
                )
    }

}