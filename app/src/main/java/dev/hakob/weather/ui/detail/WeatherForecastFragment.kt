package dev.hakob.weather.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import dev.hakob.weather.R
import dev.hakob.weather.di.ViewModelFactory
import dev.hakob.weather.extensions.toCelsius
import kotlinx.android.synthetic.main.fragment_forecast.*
import javax.inject.Inject

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.ui.detail
 */

class WeatherForecastFragment: DaggerFragment() {

    private val args by navArgs<WeatherForecastFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: WeatherForecastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityId = args.cityId
        viewModel = ViewModelProviders.of(this, viewModelFactory)[WeatherForecastViewModel::class.java]
        viewModel.start(cityId)

        viewModel.currentWeather.observe(this, Observer {
            cityName.text = it.cityName
            currentTemp.text = it.temperature.temp?.toCelsius()
            minTemp.text = it.temperature.tempMin?.toCelsius()
            maxTemp.text = it.temperature.tempMax?.toCelsius()
        })
    }

}