package com.uin.footballmatchschedule

import com.uin.footballmatchschedule.repository.TesRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.uin.footballmatchschedule.model.TestResult
import com.uin.footballmatchschedule.mvp.Presenter
import com.uin.footballmatchschedule.mvp.View
import com.uin.footballmatchschedule.repository.TesRepository
import org.junit.Before

import org.junit.Test
import org.mockito.*

class TestRequest {

    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var matchRepository: TesRepository

    @Mock
    private lateinit var matchResponse: TestResult

    private lateinit var matchPresenter: Presenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        matchPresenter = Presenter(view, matchRepository)
    }

    @Test
    fun getMatchLoadedTest() {

        val id = "4328"

        matchPresenter.getMatch(id)

        argumentCaptor<TesRepositoryCallback<TestResult?>>().apply {

            verify(matchRepository).getNextMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onDataLoaded(matchResponse)
        Mockito.verify(view).onHideLoading()
    }

    @Test
    fun getMatchErrorTest() {
        matchPresenter.getMatch("")

        argumentCaptor<TesRepositoryCallback<TestResult?>>().apply {

            verify(matchRepository).getNextMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onDataError()
        Mockito.verify(view).onHideLoading()
    }
}