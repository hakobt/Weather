package dev.hakob.weather.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.hakob.weather.ui.detail.WeatherForecastFragment
import dev.hakob.weather.ui.list.WeatherListFragment


/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.di
 */

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun weatherListFragment(): WeatherListFragment

    @ContributesAndroidInjector
    abstract fun weatherForecastFragment(): WeatherForecastFragment
}