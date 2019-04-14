package dev.hakob.weather.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.hakob.weather.data.entity.UserWeatherEntity

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.db
 */

@Dao
interface WeatherDao {

    @Query("SELECT * FROM user_weather WHERE cityId = :cityId LIMIT 1")
    fun getWeatherForCityWithId(cityId: Int): LiveData<UserWeatherEntity>

    @Query("SELECT * FROM user_weather")
    fun getAllCitiesWithWeather(): LiveData<List<UserWeatherEntity>>

    @Query("SELECT cityId FROM user_weather")
    fun getAllCityIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(userWeatherEntity: UserWeatherEntity)

    @Query("DELETE FROM user_weather WHERE cityId = :cityId")
    fun deleteCityWithId(cityId: Int)
}