package dev.hakob.weather.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CurrentWeatherResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("main")
    val main: Temperature? = null,

    @field:SerializedName("id")
    val id: Int
)

@Keep
data class BulkWeatherResponse(
    @SerializedName("list") val list: List<CurrentWeatherResponse>
)