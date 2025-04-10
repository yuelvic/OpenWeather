package xyz.mynt.openweather.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.mynt.openweather.history.domain.model.WeatherHistory
import xyz.mynt.openweather.home.domain.model.Weather

@Entity(tableName = "weather")
data class WeatherEntity(
    val id: Int,
    val locationName: String,
    val temperature: String,
    val description: String,
    val timestamp: String,
    @PrimaryKey val pk: Long,
) {
    fun toDomain(): WeatherHistory = WeatherHistory(
        id = id,
        locationName = locationName,
        temperature = temperature,
        description = description,
        timestamp = timestamp
    )
}

fun Weather.toEntity(): WeatherEntity = WeatherEntity(
    id = id,
    locationName = locationName,
    temperature = temperature.current,
    description = forecast.status,
    timestamp = timestamp,
    pk = pk
)
