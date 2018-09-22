package com.uin.footballmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

open class Result{
    /*@SerializedName("events")
    @Expose
    open var events: List<Event>? = null*/

    @SerializedName("events")
    @Expose
    open var events: List<Event>? = null

    @SerializedName("teams")
    @Expose
    open var teams: List<Detail>? = null

}