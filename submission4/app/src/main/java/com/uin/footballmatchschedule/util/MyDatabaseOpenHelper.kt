package com.uin.footballmatchschedule.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.uin.footballmatchschedule.model.Favorite
import org.jetbrains.anko.db.*


class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.idEvent to TEXT + UNIQUE,
                Favorite.idSoccerXML to TEXT,
                Favorite.strEvent to TEXT,
                Favorite.strFilename to TEXT,
                Favorite.strSport to TEXT,
                Favorite.idLeague to TEXT,
                Favorite.strLeague to TEXT,
                Favorite.strSeason to TEXT,
                Favorite.strHomeTeam to TEXT,
                Favorite.strAwayTeam to TEXT,
                Favorite.intHomeScore to TEXT,
                Favorite.intRound to TEXT,
                Favorite.intAwayScore to TEXT,
                Favorite.strHomeGoalDetails to TEXT,
                Favorite.strHomeRedCards to TEXT,
                Favorite.strHomeYellowCards to TEXT,
                Favorite.strHomeLineupGoalkeeper to TEXT,
                Favorite.strHomeLineupDefense to TEXT,
                Favorite.strHomeLineupMidfield to TEXT,
                Favorite.strHomeLineupForward to TEXT,
                Favorite.strHomeLineupSubstitutes to TEXT,
                Favorite.strHomeFormation to TEXT,
                Favorite.strAwayRedCards to TEXT,
                Favorite.strAwayYellowCards to TEXT,
                Favorite.strAwayGoalDetails to TEXT,
                Favorite.strAwayLineupGoalkeeper to TEXT,
                Favorite.strAwayLineupDefense to TEXT,
                Favorite.strAwayLineupMidfield to TEXT,
                Favorite.strAwayLineupForward to TEXT,
                Favorite.strAwayLineupSubstitutes to TEXT,
                Favorite.strAwayFormation to TEXT,
                Favorite.intHomeShots to TEXT,
                Favorite.intAwayShots to TEXT,
                Favorite.dateEvent to TEXT,
                Favorite.strDate to TEXT,
                Favorite.strTime to TEXT,
                Favorite.idHomeTeam to TEXT,
                Favorite.idAwayTeam to TEXT,
                Favorite.imgHome to TEXT,
                Favorite.imgAway to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)