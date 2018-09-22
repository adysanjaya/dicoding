package com.uin.footballmatchschedule.repository

import com.uin.footballmatchschedule.model.TestResult

interface TesRepositoryCallback<Any> {

    fun onDataLoaded(data: TestResult)
    fun onDataError()
}