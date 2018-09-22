package com.uin.footballmatchschedule.model

data class Favorite(
        val id: Long?,
        val idEvent: String?,
        val idSoccerXML: String?,
        val strEvent: String?,
        val strFilename: String?,
        val strSport: String?,
        val idLeague: String?,
        val strLeague: String?,
        val strSeason: String?,
        val strHomeTeam: String?,
        val strAwayTeam: String?,
        val intHomeScore: String?,
        val intRound: String?,
        val intAwayScore: String?,
        val strHomeGoalDetails: String?,
        val strHomeRedCards: String?,
        val strHomeYellowCards: String?,
        val strHomeLineupGoalkeeper: String?,
        val strHomeLineupDefense: String?,
        val strHomeLineupMidfield: String?,
        val strHomeLineupForward: String?,
        val strHomeLineupSubstitutes: String?,
        val strHomeFormation: String?,
        val strAwayRedCards: String?,
        val strAwayYellowCards: String?,
        val strAwayGoalDetails: String?,
        val strAwayLineupGoalkeeper: String?,
        val strAwayLineupDefense: String?,
        val strAwayLineupMidfield: String?,
        val strAwayLineupForward: String?,
        val strAwayLineupSubstitutes: String?,
        val strAwayFormation: String?,
        val intHomeShots: String?,
        val intAwayShots: String?,
        val dateEvent: String?,
        val strDate: String?,
        val strTime: String?,
        val idHomeTeam: String?,
        val idAwayTeam: String?,
        val imgHome: String?,
        val imgAway: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val idEvent: String = "ID_EVENT"
        const val idSoccerXML: String = "ID_SOCCER"
        const val strEvent: String = "STR_EVENT"
        const val strFilename: String = "STR_FILENAME"
        const val strSport: String = "STR_SPORT"
        const val idLeague: String = "ID_LEAGUE"
        const val strLeague: String = "STR_LEAGUE"
        const val strSeason: String = "STR_SEASON"
        const val strHomeTeam: String = "STR_HOMETEAM"
        const val strAwayTeam: String = "STR_AWAYTEAM"
        const val intHomeScore: String = "INT_HOMESCORE"
        const val intRound: String = "INT_ROUND"
        const val intAwayScore: String = "INT_AWAYSCORE"
        const val strHomeGoalDetails: String = "STR_HOMEGOALDETAILS"
        const val strHomeRedCards: String = "STR_HOMEREDCARS"
        const val strHomeYellowCards: String = "STR_HOMEYELLOWCARDS"
        const val strHomeLineupGoalkeeper: String = "STR_HOMELINEUPGK"
        const val strHomeLineupDefense: String = "STR_HOMELINEUPDEF"
        const val strHomeLineupMidfield: String = "STR_HOMELINEUPMID"
        const val strHomeLineupForward: String = "STR_HOMELINEUPFOR"
        const val strHomeLineupSubstitutes: String = "STR_HOMELINEUPSUB"
        const val strHomeFormation: String = "STR_HOMEFORMATION"
        const val strAwayRedCards: String = "STR_AWAYREDCARDS"
        const val strAwayYellowCards: String = "STR_AWAYYELLOWCARDS"
        const val strAwayGoalDetails: String = "STR_AWAYGOALDETAILS"
        const val strAwayLineupGoalkeeper: String = "STR_AWAYLINEUPGK"
        const val strAwayLineupDefense: String = "STR_AWAYLINEUPDEF"
        const val strAwayLineupMidfield: String = "STR_AWAYLINEUPMID"
        const val strAwayLineupForward: String = "STR_AWAYLINEUPFOR"
        const val strAwayLineupSubstitutes: String = "STR_AWAYLINEUPSUB"
        const val strAwayFormation: String = "STR_AWAYFORMATION"
        const val intHomeShots: String = "INT_HOMESHOTS"
        const val intAwayShots: String = "INT_AWAYSHOTS"
        const val dateEvent: String = "DATE_EVENT"
        const val strDate: String = "STR_DATE"
        const val strTime: String = "STR_TIME"
        const val idHomeTeam: String = "ID_HOMETEAM"
        const val idAwayTeam: String = "ID_AWAYTEAN"
        const val imgHome: String = "IMG_HOME"
        const val imgAway: String = "IMG_AWAY"
    }
}