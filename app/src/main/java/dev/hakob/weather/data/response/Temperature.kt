package dev.hakob.weather.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Temperature(

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("temp_min")
	val tempMin: Double? = null,

	@field:SerializedName("temp_max")
	val tempMax: Double? = null
)