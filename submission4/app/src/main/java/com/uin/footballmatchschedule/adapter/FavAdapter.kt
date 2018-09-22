package com.uin.footballmatchschedule.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.uin.footballmatchschedule.R
import com.uin.footballmatchschedule.R.id.*
import com.uin.footballmatchschedule.model.Favorite
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FavAdapter(private val items: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAdapter.ViewHolder {
        return FavAdapter.ViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        private val tvTgl: TextView = containerView.find(tv_tgl)
        private val tvTeam1: TextView = containerView.find(tv_team1)
        private val tvTeam2: TextView = containerView.find(tv_team2)
        private val tvSkor1: TextView = containerView.find(tv_skor1)
        private val tvSkor2: TextView = containerView.find(tv_skor2)
        private val tvVs: TextView = containerView.find(tv_vs)
        private val llSkor: LinearLayout = containerView.find(ll_skor)

        fun bindItem(items: Favorite, listener: (Favorite) -> Unit) {

            tvTgl.text = items.dateEvent
            tvTeam1.text = items.strHomeTeam
            tvTeam2.text = items.strAwayTeam
            try {
                tvSkor1.text = items.intHomeScore
                tvSkor2.text = items.intAwayScore
                tvVs.text = "vs";
                llSkor.visibility = View.VISIBLE
            }
            catch (e : NullPointerException){
                llSkor.visibility = View.INVISIBLE
            }

            containerView.setOnClickListener { listener(items) }
        }
    }
}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                //app:cardElevation = 1dp //not support attribute
                lparams{
                    width = matchParent
                    bottomMargin = dip(10)
                }
                backgroundColor = Color.WHITE
                linearLayout {
                    horizontalPadding = dip(16)
                    bottomPadding = dip(16)
                    topPadding = dip(10)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER
                    backgroundColor = Color.WHITE
                    textView {
                        id = R.id.tv_tgl
                        //tools:text = Date //not support attribute
                        textColor = resources.getColor(R.color.colorPrimary)
                    }.lparams {
                        bottomMargin = dip(10)
                    }
                    linearLayout {
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL
                        //android:weightSum = 3 //not support attribute
                        textView {
                            id = R.id.tv_team1
                            gravity = Gravity.CENTER
                            //app:layout_constraintEnd_toStartOf = @+id/linearLayout //not support attribute
                            //app:layout_constraintStart_toStartOf = parent //not support attribute
                            //tools:text = Barcelona FC //not support attribute
                            //android:lines = 1 //not support attribute
                            //android:ellipsize = end //not support attribute
                        }.lparams(width = 0) {
                            weight = 1f
                        }
                        linearLayout {
                            id = R.id.ll_skor
                            gravity = Gravity.CENTER
                            orientation = LinearLayout.HORIZONTAL
                            //android:weightSum = 3 //not support attribute
                            visibility = View.VISIBLE
                            textView {
                                id = R.id.tv_skor1
                                gravity = Gravity.CENTER
                                //tools:text = 2 //not support attribute
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(width = 0) {
                                weight = 1f
                            }
                            textView {
                                id = R.id.tv_vs
                                gravity = Gravity.CENTER
                                //tools:text = vs //not support attribute
                            }.lparams(width = 0) {
                                weight = 1f
                            }
                            textView {
                                id = R.id.tv_skor2
                                gravity = Gravity.CENTER
                                //tools:text = 1 //not support attribute
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(width = 0) {
                                weight = 1f
                            }
                        }.lparams(width = 0) {
                            weight = 1f
                        }
                        textView {
                            id = R.id.tv_team2
                            gravity = Gravity.CENTER
                            //tools:text = Real Madrid FC //not support attribute
                            //android:lines = 1 //not support attribute
                            //android:ellipsize = end //not support attribute
                        }.lparams(width = 0) {
                            weight = 1f
                        }
                    }.lparams(width = matchParent)
                }.lparams(width = matchParent)
            }
        }
    }
}