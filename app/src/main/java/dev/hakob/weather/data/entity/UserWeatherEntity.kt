package dev.hakob.weather.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.hakob.weather.data.response.Temperature

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.data
 */

@Entity(
    tableName = "user_weather"
)
data class UserWeatherEntity(
    @PrimaryKey val cityId: Int,
    val cityName: String,
    @Embedded val temperature: Temperature
)