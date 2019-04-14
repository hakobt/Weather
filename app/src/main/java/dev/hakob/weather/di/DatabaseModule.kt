package dev.hakob.weather.di

import android.content.Context
import dagger.Provides
import androidx.room.Room
import dagger.Module
import dev.hakob.weather.db.WeatherDao
import dev.hakob.weather.db.WeatherDb
import javax.inject.Singleton



/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.di
 */

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun db(context: Context): WeatherDb {
        return Room.databaseBuilder(context, WeatherDb::class.java, "weather.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun newsDao(db: WeatherDb): WeatherDao {
        return db.weatherDao()
    }
}