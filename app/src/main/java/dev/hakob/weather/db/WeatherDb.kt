package dev.hakob.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.hakob.weather.data.entity.UserWeatherEntity

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.db
 */

@Database(
    entities =
    [
        UserWeatherEntity::class
    ],
    version = 7,
    exportSchema = false
)
abstract class WeatherDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}