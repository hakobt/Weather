package dev.hakob.weather.data

import android.location.Location
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import dev.hakob.weather.api.WeatherApi
import dev.hakob.weather.data.entity.UserWeatherEntity
import dev.hakob.weather.data.response.CurrentWeatherResponse
import dev.hakob.weather.db.WeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.data
 */


@Singleton
class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao
) {

    init {
        // refresh all user added city weathers
        refreshAllStoredCityWeathers()
    }

    val weatherList = weatherDao.getAllCitiesWithWeather()

    /**
     * Gets all the city ids from database and refreshes to get the latest info.
     */
    private fun refreshAllStoredCityWeathers() = runBlocking {
        launch(Dispatchers.IO) {
            val ids = weatherDao.getAllCityIds()

            if (ids.isNullOrEmpty()) {
                return@launch
            }

            val query = ids.toString().removeSuffix("]").removePrefix("[").replace(" ", "")
            val request = api.getWeatherForCitiesAsync(query)
            val response = request.await()

            response.body()?.list?.forEach(this@WeatherRepository::writeToDb)
        }
    }

    /**
     * requests the weather by city name and stores into db.
     */
    fun requestWeather(name: String) = runBlocking {
        launch(Dispatchers.IO) {
            val request = api.getWeatherWithCityNameAsync(name)
            val response = request.await()

            if (!response.isSuccessful) {
                return@launch
            }

            val body = response.body() ?: return@launch
            writeToDb(body)
        }
    }

    /**
     * Stores the api response to database
     */
    @WorkerThread
    private fun writeToDb(weatherResponse: CurrentWeatherResponse) {
        val entity = UserWeatherEntity(
            weatherResponse.id,
            weatherResponse.name,
            weatherResponse.main ?: return
        )
        weatherDao.insertWeather(entity)
    }

    /**
     * Deletes the city from database.
     */
    fun deleteCityWithId(cityId: Int) = runBlocking {
        launch(Dispatchers.IO) {
            weatherDao.deleteCityWithId(cityId)
        }
    }

    /**
     * Adds the city to watch list from user's location.
     */
    fun addCityWithLocation(location: Location) = runBlocking {
        launch(Dispatchers.IO) {
            val request = api.getWeatherWithLatLngAsync(location.latitude, location.longitude)

            val response = request.await()

            response.body()?.let(this@WeatherRepository::writeToDb)
        }
    }

    /**
     * Gets the city with id from database.
     */
    fun getWeatherWithId(cityId: Int): LiveData<UserWeatherEntity> {
        return weatherDao.getWeatherForCityWithId(cityId)
    }
}