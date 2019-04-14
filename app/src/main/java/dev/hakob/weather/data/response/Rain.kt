package dev.hakob.weather.data.response

import com.google.gson.annotations.SerializedName

data class Rain(

    @field:SerializedName("3h")
    val threeHour: Int? = null,

    @field:SerializedName("1h")
    val oneHour: Int? = null
)