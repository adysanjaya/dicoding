package com.uin.footballmatchschedule.fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.uin.footballmatchschedule.DetailActivity

import com.uin.footballmatchschedule.R
import com.uin.footballmatchschedule.adapter.PrevAdapter
import com.uin.footballmatchschedule.model.Event
import com.uin.footballmatchschedule.model.Result
import com.uin.footballmatchschedule.rest.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class FragNext : Fragment(), (Event) -> Unit {

    private var disposable: Disposable? = null
    val TAG : String = "FragNext"
    var listEvent : List<Event>? = null
    var adapter : PrevAdapter? = null
    lateinit var rootView : View
    lateinit var recycler : RecyclerView
    lateinit var swipeRefresh : SwipeRefreshLayout
    lateinit var progress : RelativeLayout
    lateinit var refresh: LinearLayout

    private val service by lazy {
        Service.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_list, container, false)

        this.rootView = v.root
        this.recycler = v.rec
        this.swipeRefresh = v.swipeRefresh
        this.progress = v.progress
        this.refresh = v.refresh

        recycler.layoutManager = LinearLayoutManager(activity)
        beginSearch()

        swipeRefresh.setOnRefreshListener({
            swipeRefresh.isRefreshing = true
            beginSearch()
        })
        return v;
    }


    companion object {
        fun newInstance(): FragNext = FragNext()
    }

    private fun beginSearch() {
        disposable = service.getNextMatch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{showLoading()}
                .subscribe(
                        { result -> showResult(result) },
                        { error -> aksiError(error.message) }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    fun showResult(result: Result){
        try {
            listEvent = result.events
            adapter = PrevAdapter(context!!, listEvent!!,this)
            recycler.adapter = adapter
            adapter?.notifyDataSetChanged()

            aksiSukses()
        }
        catch (e : NullPointerException){

        }
    }

    fun showLoading(){
        progress.visibility = View.VISIBLE
        swipeRefresh.isRefreshing = false
        refresh.visibility = View.GONE
        recycler.visibility = View.GONE
    }

    fun aksiSukses(){
        progress.visibility = View.GONE
        swipeRefresh.isRefreshing = false
        refresh.visibility = View.GONE
        recycler.visibility = View.VISIBLE
    }

    fun aksiError(message: String?) {
        swipeRefresh.isRefreshing = false
        refresh.visibility = View.VISIBLE
        progress.visibility = View.GONE
        recycler.visibility = View.GONE
        Log.d(TAG, message!!)
    }

    override fun invoke(e: Event) {
        var intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("data" , e)
        startActivity(intent)
    }
}
