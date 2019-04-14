package dev.hakob.weather.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.network
 *
 * An OkHttp interceptor that adds the api key to every request, as required by OpenWeatherApi
 */
class AuthorizationInterceptor: Interceptor {

    private val key = "appid"
    private val appId = "197b39149c4224a63ee2369fbde75429"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newHttpUrl = request.url().newBuilder().addQueryParameter(key, appId).build()

        val newRequest = request.newBuilder().url(newHttpUrl).build()

        return chain.proceed(newRequest)
    }
}