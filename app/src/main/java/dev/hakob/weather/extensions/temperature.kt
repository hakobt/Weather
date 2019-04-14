package dev.hakob.weather.extensions

/**
 * Created by Hakob Tovmasyan on 4/14/19
 * Package dev.hakob.weather.extensions
 */

fun Double.toCelsius(): String = (this - 273.15).toInt().toString() + "°"