package com.uin.footballmatchschedule.mvp

import com.uin.footballmatchschedule.model.TestResult
import com.uin.footballmatchschedule.repository.TesRepositoryCallback

interface View : TesRepositoryCallback<TestResult> {
    fun onShowLoading()
    fun onHideLoading()
}