package dev.hakob.weather.ui.list

import androidx.lifecycle.ViewModel
import dev.hakob.weather.data.WeatherRepository
import dev.hakob.weather.location.LocationManager
import javax.inject.Inject

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.ui
 */

class WeatherListViewModel @Inject constructor(
    private val repo: WeatherRepository,
    private val locationManager: LocationManager
) : ViewModel() {

    val weatherList = repo.weatherList

    fun addCity(name: String) {
        repo.requestWeather(name)
    }

    fun deleteCity(cityId: Int?): Boolean {
        cityId?.let {
            repo.deleteCityWithId(it)
            return true
        } ?: return false
    }

    fun onLocationPermissionGranted() {
        locationManager.requestLocationUpdates {
            repo.addCityWithLocation(it)
        }
    }

    fun onLocationPermissionDenied() {
        // do nothing
    }
}