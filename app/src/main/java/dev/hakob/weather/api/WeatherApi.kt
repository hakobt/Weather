package dev.hakob.weather.api

import dev.hakob.weather.data.response.BulkWeatherResponse
import dev.hakob.weather.data.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.api
 */
interface WeatherApi {

    @GET("weather")
    fun getWeatherWithLatLngAsync(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Deferred<Response<CurrentWeatherResponse>>

    @GET("weather")
    fun getWeatherWithCityNameAsync(
        @Query("q") cityName: String
    ): Deferred<Response<CurrentWeatherResponse>>

    // example: http://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric
    @GET("group")
    fun getWeatherForCitiesAsync(
            @Query("id") ids: String
    ): Deferred<Response<BulkWeatherResponse>>
}