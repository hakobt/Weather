package dev.hakob.weather.ui.list

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.hakob.weather.R
import dev.hakob.weather.data.entity.UserWeatherEntity
import dev.hakob.weather.extensions.toCelsius

/**
 * Created by Hakob Tovmasyan on 4/13/19
 * Package dev.hakob.weather.ui.list
 */
class WeatherListAdapter(private val clickHandler: (UserWeatherEntity) -> Unit) : ListAdapter<UserWeatherEntity, WeatherViewHolder>(WeatherDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_weather_item, parent, false)
        return WeatherViewHolder(view).apply {
            view.setOnClickListener { clickHandler(getItem(adapterPosition)) }
        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}

class WeatherDiffUtil : DiffUtil.ItemCallback<UserWeatherEntity>() {
    override fun areItemsTheSame(oldItem: UserWeatherEntity,
                                 newItem: UserWeatherEntity) = oldItem.cityId == newItem.cityId

    override fun areContentsTheSame(oldItem: UserWeatherEntity,
                                    newItem: UserWeatherEntity) = oldItem == newItem
}

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val temperatureView = view.findViewById<TextView>(R.id.temperature)
    private val cityNameView = view.findViewById<TextView>(R.id.cityName)
    private val background = view.findViewById<ImageView>(R.id.background)

    fun bind(userWeatherEntity: UserWeatherEntity) {
        temperatureView.text = userWeatherEntity.temperature.temp?.toCelsius()
        cityNameView.text = userWeatherEntity.cityName


    }
}