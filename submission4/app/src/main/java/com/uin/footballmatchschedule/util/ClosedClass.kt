package com.uin.footballmatchschedule.util

import io.reactivex.disposables.Disposable

class ClosedClass() {

    var prop: Int = 0
    private var disposable: Disposable? = null

    fun doSomething() : Int {
        return 2+2
    }
}