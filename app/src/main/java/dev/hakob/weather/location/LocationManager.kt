package dev.hakob.weather.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.location
 */

@Singleton
class LocationManager @Inject constructor(private val context: Context) {

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(callback: (Location) -> Unit) {
        val locationProvider = LocationServices.getFusedLocationProviderClient(context)
        locationProvider.lastLocation.addOnSuccessListener {
            callback(it)
        }
    }
}