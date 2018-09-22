package com.uin.footballmatchschedule

import android.icu.text.SimpleDateFormat
import android.util.Log
import com.uin.footballmatchschedule.fragment.FragPrev
import com.uin.footballmatchschedule.model.Result
import com.uin.footballmatchschedule.rest.Service
import com.uin.footballmatchschedule.util.ClosedClass
import com.uin.footballmatchschedule.util.Utils
import org.junit.Test

import org.junit.Assert.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class ExampleUnitTest {

//    private lateinit var d: FragPrev
//    private lateinit var e: ClosedClass

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
//        d = FragPrev()
//        e = ClosedClass()
    }

    @Test
    fun testResult(){
        val service by lazy {
            Service.create()
        }
        service.getPrevMatch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> showResult(result) },
                        { error -> aksiError(error.message) }
                )
    }

    fun showResult(result: Result){
    }

    fun aksiError(message: String?){

    }
}
