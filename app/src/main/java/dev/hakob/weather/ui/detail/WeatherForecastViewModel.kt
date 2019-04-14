package dev.hakob.weather.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.hakob.weather.data.WeatherRepository
import dev.hakob.weather.data.entity.UserWeatherEntity
import javax.inject.Inject

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.ui
 */

class WeatherForecastViewModel @Inject constructor(
        private val repo: WeatherRepository
) : ViewModel() {

    lateinit var currentWeather: LiveData<UserWeatherEntity>

    fun start(cityId: Int) {
        currentWeather = repo.getWeatherWithId(cityId)
    }
}