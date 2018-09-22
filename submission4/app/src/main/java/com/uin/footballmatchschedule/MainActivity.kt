package com.uin.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.widget.FrameLayout
import com.uin.footballmatchschedule.R.id.*
import com.uin.footballmatchschedule.fragment.FragFav
import com.uin.footballmatchschedule.fragment.FragNext
import com.uin.footballmatchschedule.fragment.FragPrev
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.design.bottomNavigationView

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainAct"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = navigation_prev
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            navigation_prev -> {
                supportActionBar?.title = "Prev Match"
                openFragment(FragPrev())
                return@OnNavigationItemSelectedListener true
            }
            navigation_next-> {
                supportActionBar?.title = "Next Match"
                openFragment(FragNext())
                return@OnNavigationItemSelectedListener true
            }
            navigation_fav-> {
                supportActionBar?.title = "Favorite Match"
                openFragment(FragFav())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, TAG)
                .commitAllowingStateLoss()

    }
}
