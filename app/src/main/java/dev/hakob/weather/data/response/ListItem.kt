package dev.hakob.weather.data.response

import com.google.gson.annotations.SerializedName

data class ListItem(

	@field:SerializedName("dt")
	val dt: Int? = null,

	@field:SerializedName("dt_txt")
	val dtTxt: String? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem>? = null,

	@field:SerializedName("main")
	val main: Temperature? = null
)