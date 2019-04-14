package dev.hakob.weather.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.hakob.weather.MainActivity

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.di
 */

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}