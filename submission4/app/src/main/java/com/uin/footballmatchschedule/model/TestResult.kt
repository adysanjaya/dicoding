package com.uin.footballmatchschedule.model

import com.google.gson.annotations.SerializedName
import com.uin.footballmatchschedule.model.Result

data class TestResult(

	@field:SerializedName("events")
	val events: List<Result>? = null
)