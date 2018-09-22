package com.uin.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        var idEvent: String?,
        var idSoccerXML: String?,
        var strEvent: String?,
        var strFilename: String?,
        var strSport: String?,
        var idLeague: String?,
        var strLeague: String?,
        var strSeason: String?,
        var strHomeTeam: String?,
        var strAwayTeam: String?,
        var intHomeScore: String?,
        var intRound: String?,
        var intAwayScore: String?,
        var strHomeGoalDetails: String?,
        var strHomeRedCards: String?,
        var strHomeYellowCards: String?,
        var strHomeLineupGoalkeeper: String?,
        var strHomeLineupDefense: String?,
        var strHomeLineupMidfield: String?,
        var strHomeLineupForward: String?,
        var strHomeLineupSubstitutes: String?,
        var strHomeFormation: String?,
        var strAwayRedCards: String?,
        var strAwayYellowCards: String?,
        var strAwayGoalDetails: String?,
        var strAwayLineupGoalkeeper: String?,
        var strAwayLineupDefense: String?,
        var strAwayLineupMidfield: String?,
        var strAwayLineupForward: String?,
        var strAwayLineupSubstitutes: String?,
        var strAwayFormation: String?,
        var intHomeShots: String?,
        var intAwayShots: String?,
        var dateEvent: String?,
        var strDate: String?,
        var strTime: String?,
        var idHomeTeam: String?,
        var idAwayTeam: String?
) : Parcelable