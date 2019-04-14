package dev.hakob.weather.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.di
 */

@Module
abstract class AppModule {

    @Binds
    abstract fun context(app: Application): Context
}