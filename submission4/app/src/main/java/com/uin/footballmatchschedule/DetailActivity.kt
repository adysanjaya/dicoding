package com.uin.footballmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.EventLog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.uin.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.uin.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.uin.footballmatchschedule.adapter.PrevAdapter
import com.uin.footballmatchschedule.model.Event
import com.uin.footballmatchschedule.model.Favorite
import com.uin.footballmatchschedule.model.Result
import com.uin.footballmatchschedule.rest.Service
import com.uin.footballmatchschedule.util.database
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.design.snackbar

import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity() {

    private lateinit var event : Event
    private lateinit var id: String
    private var TAG  : String = "DetailAct"
    private var imgHome : String? = null
    private var imgAway : String? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var disposable: Disposable? = null
    private val service by lazy {
        Service.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match Detail"


        if(intent.getStringExtra("id")==null){
            event = intent.getParcelableExtra("data")

            id = event.idEvent?:""
            searchLogo1(event.idHomeTeam!!)
            searchLogo2(event.idAwayTeam!!)
        }
        else{
            val idEvent : String = intent.getStringExtra("id")
            database.use {
                val result = select(Favorite.TABLE_FAVORITE)
                        .whereArgs("(ID_EVENT = {id})",
                                "id" to idEvent)
                val favorite = result.parseList(classParser<Favorite>())
                if (!favorite.isEmpty()) {
                    val data = favorite.get(0)

                    event = Event(
                                    data.idEvent,
                                    data.idSoccerXML,
                                    data.strEvent,
                                    data.strFilename,
                                    data.strSport,
                                    data.idLeague,
                                    data.strLeague,
                                    data.strSeason,
                                    data.strHomeTeam,
                                    data.strAwayTeam,
                                    data.intHomeScore,
                                    data.intRound,
                                    data.intAwayScore,
                                    data.strHomeGoalDetails,
                                    data.strHomeRedCards,
                                    data.strHomeYellowCards,
                                    data.strHomeLineupGoalkeeper,
                                    data.strHomeLineupDefense,
                                    data.strHomeLineupMidfield,
                                    data.strHomeLineupForward,
                                    data.strHomeLineupSubstitutes,
                                    data.strHomeFormation,
                                    data.strAwayRedCards,
                                    data.strAwayYellowCards,
                                    data.strAwayGoalDetails,
                                    data.strAwayLineupGoalkeeper,
                                    data.strAwayLineupDefense,
                                    data.strAwayLineupMidfield,
                                    data.strAwayLineupForward,
                                    data.strAwayLineupSubstitutes,
                                    data.strAwayFormation,
                                    data.intHomeShots,
                                    data.intAwayShots,
                                    data.dateEvent,
                                    data.strDate,
                                    data.strTime,
                                    data.idHomeTeam,
                                    data.idAwayTeam)
                    id = event.idEvent?:""

                    imgHome = data.imgHome
                    imgAway = data.imgAway
                    Glide.with(this@DetailActivity).load(imgHome).apply(RequestOptions().override(100,100).fitCenter()).into(iv1)
                    Glide.with(this@DetailActivity).load(imgAway).apply(RequestOptions().override(100,100).fitCenter()).into(iv2)

                    aksiSukses()
                }
            }
        }

        try {
            tv_tgl.text = event.dateEvent
            tv_nama1.text = event.strHomeTeam
            tv_nama2.text = event.strAwayTeam
            tv_skor1.text = event.intHomeScore
            tv_skor2.text = event.intAwayScore
            tv_formasi1.text = event.strHomeFormation
            tv_formasi2.text = event.strAwayFormation
            tv_goals1.text = "${event.strHomeGoalDetails?.replace(";", "\n")}"
            tv_goals2.text = "${event.strAwayGoalDetails?.replace(";", "\n")}"
            tv_shots1.text = event.intHomeShots
            tv_shots2.text = event.intAwayShots
            tv_gk1.text = "${event.strHomeLineupGoalkeeper?.replace(";", "\n")}"
            tv_gk2.text = "${event.strAwayLineupGoalkeeper?.replace(";", "\n")}"
            tv_defense1.text = "${event.strHomeLineupDefense?.replace(";", "\n")}"
            tv_defense2.text = "${event.strAwayLineupDefense?.replace(";", "\n")}"
            tv_midfield1.text = "${event.strHomeLineupMidfield?.replace(";", "\n")}"
            tv_midfield2.text = "${event.strAwayLineupMidfield?.replace(";", "\n")}"
            tv_forward1.text = "${event.strHomeLineupForward?.replace(";", "\n")}"
            tv_forward2.text = "${event.strAwayLineupForward?.replace(";", "\n")}"
            tv_substitutes1.text = "${event.strHomeLineupSubstitutes?.replace(";", "\n")}"
            tv_substitutes2.text = "${event.strAwayLineupSubstitutes?.replace(";", "\n")}"

            favoriteState()

            swipeRefresh.setOnRefreshListener({
                swipeRefresh.isRefreshing = true
                searchLogo1(event.idHomeTeam!!)
                searchLogo2(event.idAwayTeam!!)
            })
        }
        catch (e:UninitializedPropertyAccessException){
            aksiError("data kosong")
        }
    }

    private fun searchLogo1(idTeam : String) {
        disposable = service.getLogo(idTeam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            imgHome = result.teams?.get(0)?.strTeamLogo.toString()
                            Glide.with(this).load(imgHome).apply(RequestOptions().override(100,100).fitCenter()).into(iv1)
                            aksiSukses()
                            },
                        { error -> aksiError(error.message) }

                )
    }

    private fun searchLogo2(idTeam : String) {
        disposable = service.getLogo(idTeam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            imgAway = result.teams?.get(0)?.strTeamLogo.toString()
                            Glide.with(this).load(imgAway).apply(RequestOptions().override(100,100).fitCenter()).into(iv2)
                            aksiSukses()},
                        { error -> aksiError(error.message) }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
            }

            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun aksiSukses(){
        progress.visibility = View.GONE
        swipeRefresh.isRefreshing = false
        refresh.visibility = View.GONE
        parentView.visibility = View.VISIBLE
    }

    fun aksiError(message: String?) {
        swipeRefresh.isRefreshing = false
        refresh.visibility = View.VISIBLE
        progress.visibility = View.GONE
        parentView.visibility = View.GONE
        refresh_message.text = message
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.idEvent to event.idEvent,
                        Favorite.idEvent to event.idEvent,
                        Favorite.idSoccerXML to event.idSoccerXML ,
                        Favorite.strEvent to event.strEvent ,
                        Favorite.strFilename to event.strFilename ,
                        Favorite.strSport to event.strSport ,
                        Favorite.idLeague to event.idLeague ,
                        Favorite.strLeague to event.strLeague ,
                        Favorite.strSeason to event.strSeason ,
                        Favorite.strHomeTeam to event.strHomeTeam ,
                        Favorite.strAwayTeam to event.strAwayTeam ,
                        Favorite.intHomeScore to event.intHomeScore ,
                        Favorite.intRound to event.intRound ,
                        Favorite.intAwayScore to event.intAwayScore ,
                        Favorite.strHomeGoalDetails to event.strHomeGoalDetails ,
                        Favorite.strHomeRedCards to event.strHomeRedCards ,
                        Favorite.strHomeYellowCards to event.strHomeYellowCards ,
                        Favorite.strHomeLineupGoalkeeper to event.strHomeLineupGoalkeeper ,
                        Favorite.strHomeLineupDefense to event.strHomeLineupDefense ,
                        Favorite.strHomeLineupMidfield to event.strHomeLineupMidfield ,
                        Favorite.strHomeLineupForward to event.strHomeLineupForward ,
                        Favorite.strHomeLineupSubstitutes to event.strHomeLineupSubstitutes ,
                        Favorite.strHomeFormation to event.strHomeFormation ,
                        Favorite.strAwayRedCards to event.strAwayRedCards ,
                        Favorite.strAwayYellowCards to event.strAwayYellowCards ,
                        Favorite.strAwayGoalDetails to event.strAwayGoalDetails ,
                        Favorite.strAwayLineupGoalkeeper to event.strAwayLineupGoalkeeper ,
                        Favorite.strAwayLineupDefense to event.strAwayLineupDefense ,
                        Favorite.strAwayLineupMidfield to event.strAwayLineupMidfield ,
                        Favorite.strAwayLineupForward to event.strAwayLineupForward ,
                        Favorite.strAwayLineupSubstitutes to event.strAwayLineupSubstitutes ,
                        Favorite.strAwayFormation to event.strAwayFormation ,
                        Favorite.intHomeShots to event.intHomeShots ,
                        Favorite.intAwayShots to event.intAwayShots ,
                        Favorite.dateEvent to event.dateEvent ,
                        Favorite.strDate to event.strDate ,
                        Favorite.strTime to event.strTime ,
                        Favorite.idHomeTeam to event.idHomeTeam ,
                        Favorite.idAwayTeam to event.idAwayTeam,
                        Favorite.imgHome to imgHome ,
                        Favorite.imgAway to imgAway
                )
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {id})",
                        "id" to id)
            }
            snackbar(swipeRefresh, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) {
                isFavorite = true
            }
        }
    }

}
