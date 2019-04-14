package dev.hakob.weather.data.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

    @field:SerializedName("city")
    val city: City,

    @field:SerializedName("list")
    val list: List<ListItem>
)