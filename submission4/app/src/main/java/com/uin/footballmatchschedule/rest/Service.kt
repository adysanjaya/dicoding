package com.uin.footballmatchschedule.rest


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.uin.footballmatchschedule.model.Result
import com.uin.footballmatchschedule.model.TestResult
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call

interface Service {
    //fortesting
    @GET("eventsnextleague.php")
    fun getNextMatchTest(@Query("id") id : String): Observable<TestResult?>

    @GET("eventspastleague.php?id=4328")
    fun getPrevMatch(): Observable<Result>

    @GET("eventsnextleague.php?id=4328")
    fun getNextMatch(): Observable<Result>

    @GET("lookupteam.php")
    fun getLogo(@Query("id")idTeam : String): Observable<Result>

    companion object {
        fun create(): Service {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                    .build()

            return retrofit.create(Service::class.java)
        }
    }
}