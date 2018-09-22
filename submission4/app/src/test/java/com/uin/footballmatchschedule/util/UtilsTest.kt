package com.uin.footballmatchschedule.util

import android.icu.text.SimpleDateFormat
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class UtilsTest {

    @Test
    fun toSimpleString(date: Date) {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", toSimpleString(date))
    }
}