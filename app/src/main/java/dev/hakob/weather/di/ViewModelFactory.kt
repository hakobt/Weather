package dev.hakob.weather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.di
 */

@Singleton
class ViewModelFactory @Inject constructor(
        private val viewModelProviderMap: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (viewModelProviderMap.containsKey(modelClass)) {
            viewModelProviderMap[modelClass]?.get() as T
        } else {
            throw IllegalArgumentException("$modelClass not configured in ViewModelModule")
        }
    }
}