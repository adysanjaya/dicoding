package com.uin.footballmatchschedule.fragment

import android.content.Context
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
import com.uin.footballmatchschedule.DetailActivity

import com.uin.footballmatchschedule.R
import com.uin.footballmatchschedule.R.color.colorAccent
import com.uin.footballmatchschedule.adapter.FavAdapter
import com.uin.footballmatchschedule.adapter.PrevAdapter
import com.uin.footballmatchschedule.model.Event
import com.uin.footballmatchschedule.model.Favorite
import com.uin.footballmatchschedule.model.Result
import com.uin.footballmatchschedule.rest.Service
import com.uin.footballmatchschedule.util.database
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FragFav : Fragment(), AnkoComponent<Context> {

    private var disposable: Disposable? = null
    val TAG : String = "FragFav"
//    var listEvent : List<Event>? = null
    private lateinit var adapter : FavAdapter
    private var favorites: MutableList<Favorite> = mutableListOf()

    lateinit var rootView : View
    lateinit var recycler : RecyclerView
    lateinit var swipeRefresh : SwipeRefreshLayout
    lateinit var progress : ProgressBar
    lateinit var refresh: LinearLayout

    private val service by lazy {
        Service.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavAdapter(favorites){
            ctx.startActivity<DetailActivity>("id" to "${it.idEvent}")
        }

        recycler.adapter = adapter
        showFavorite()

        swipeRefresh.onRefresh {
            swipeRefresh.isRefreshing = true
            favorites.clear()
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){

            relativeLayout {
                //tools:context = .fragment.FragPrev //not support attribute
                id = R.id.root
                this@FragFav.swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipeRefresh
                    setColorSchemeResources(colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)
                    recycler = recyclerView {
                                    lparams(width = matchParent, height = matchParent) {
                                        margin = dip(10)
                                    }
                                    id = R.id.rec
                                    visibility = View.GONE
                                    layoutManager = LinearLayoutManager(ctx)
                                }
                }.lparams(width = matchParent, height = matchParent)

                this@FragFav.progress = progressBar {
                    id = R.id.progress
                    //android:indeterminateDrawable = @drawable/progressbar //not support attribute
                }.lparams {
                    centerInParent()
                }
                this@FragFav.refresh = linearLayout {
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER
                    visibility = View.GONE
                    id = R.id.refresh
                    imageView {
                        imageResource = R.mipmap.ic_launcher
                    }
                    textView {
                        gravity = Gravity.CENTER
                        text = "Network Error"
                        textSize = 12f //sp
                    }
                }.lparams(width = matchParent) {
                    centerInParent()
                }
            }
    }


    companion object {
        fun newInstance(): FragFav = FragFav()
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            aksiSukses()
        }
    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    fun aksiSukses(){
        progress.visibility = View.GONE
        swipeRefresh.isRefreshing  = false
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

}
