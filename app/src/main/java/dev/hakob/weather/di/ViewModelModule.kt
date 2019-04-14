package dev.hakob.weather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.hakob.weather.ui.detail.CityWeatherDetailViewModel
import dev.hakob.weather.ui.list.WeatherListViewModel


/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.di
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CityWeatherDetailViewModel::class)
    abstract fun bindWeatherDetail(cityWeatherDetailViewModel: CityWeatherDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherListViewModel::class)
    abstract fun bindWeatherListViewModel(weatherListViewModel: WeatherListViewModel): ViewModel
}