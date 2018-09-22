package com.uin.footballmatchschedule.mvp

import com.uin.footballmatchschedule.model.TestResult
import com.uin.footballmatchschedule.repository.TesRepository
import com.uin.footballmatchschedule.repository.TesRepositoryCallback

class Presenter(private val view: View, private val matchRepository: TesRepository) {

    fun getMatch(id: String) {
        view.onShowLoading()
        matchRepository.getNextMatch(id, object : TesRepositoryCallback<TestResult?> {
            override fun onDataLoaded(data: TestResult) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.onHideLoading()
    }
}