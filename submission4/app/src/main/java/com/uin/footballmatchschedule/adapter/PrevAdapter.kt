package com.uin.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.content.Context
import android.os.Build
import com.uin.footballmatchschedule.R
import com.uin.footballmatchschedule.model.Event
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class PrevAdapter(private val context: Context, private val items: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<PrevAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bindItem(items: Event, listener: (Event) -> Unit) {

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd", Locale.ENGLISH)
//                val date = LocalDate.parse(items.dateEvent, formatter)
//                val format = SimpleDateFormat("dd/MM/yyy")
//                tv_tgl.text = format.format(date)
//            } else {
                tv_tgl.text = items.dateEvent
//            }

            tv_team1.text = items.strHomeTeam
            tv_team2.text = items.strAwayTeam
            try {
                tv_skor1.text = items.intHomeScore
                tv_skor2.text = items.intAwayScore
                tv_vs.text = "vs";
                ll_skor.visibility = View.VISIBLE
            }
            catch (e : NullPointerException){
                ll_skor.visibility = View.INVISIBLE
            }


//            Glide.with(containerView).load(items.image).into(image)
            containerView.setOnClickListener { listener(items) }
        }
    }

    fun Date.toSimpleString() : String {
        val format = SimpleDateFormat("dd/MM/yyy")
        return format.format(this)
    }
}