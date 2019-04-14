package dev.hakob.weather.data.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(

        @field:SerializedName("dt")
        val dt: Int? = null,

        @field:SerializedName("rain")
        val rain: Rain? = null,

        @field:SerializedName("coord")
        val coord: Coord? = null,

        @field:SerializedName("weather")
        val weather: List<WeatherItem>? = null,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("main")
        val main: Temperature? = null,

        @field:SerializedName("clouds")
        val clouds: Clouds? = null,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("sys")
        val sys: Sys? = null,

        @field:SerializedName("base")
        val base: String? = null,

        @field:SerializedName("wind")
        val wind: Wind? = null
)

data class BulkWeatherResponse(
        @SerializedName("list") val list: List<CurrentWeatherResponse>
)