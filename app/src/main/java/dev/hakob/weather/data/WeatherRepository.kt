package dev.hakob.weather.data

import android.location.Location
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
        refreshAllStoredCityWeathers()
    }

    val weatherList = weatherDao.getAllCitiesWithWeather()

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

    private fun requestWeather(name: String) = runBlocking {
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

    private fun writeToDb(weatherResponse: CurrentWeatherResponse) {
        val entity = UserWeatherEntity(
            weatherResponse.id,
            weatherResponse.name,
            weatherResponse.main ?: return
        )
        weatherDao.insertWeather(entity)
    }

    fun tryAddCity(name: String) {
        requestWeather(name)
    }

    fun deleteCityWithId(cityId: Int) = runBlocking {
        launch(Dispatchers.IO) {
            weatherDao.deleteCityWithId(cityId)
        }
    }

    fun addCityWithLocation(location: Location) = runBlocking {
        launch(Dispatchers.IO) {
            val request = api.getWeatherWithLatLngAsync(location.latitude, location.longitude)

            val response = request.await()

            response.body()?.let(this@WeatherRepository::writeToDb)
        }
    }

    fun getWeatherWithId(cityId: Int): LiveData<UserWeatherEntity> {
        return weatherDao.getWeatherForCityWithId(cityId)
    }
}