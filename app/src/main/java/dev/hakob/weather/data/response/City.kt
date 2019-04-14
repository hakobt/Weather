package dev.hakob.weather.data.response

import com.google.gson.annotations.SerializedName

data class City(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("coord")
	val coord: Coord? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("cnt")
	val cnt: Int? = null,

	@field:SerializedName("cod")
	val cod: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("message")
	val message: Double? = null,

	@field:SerializedName("list")
	val list: List<ListItem>? = null
)