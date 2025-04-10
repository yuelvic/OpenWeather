package xyz.mynt.openweather.home.data.remote.dto

import com.google.gson.annotations.SerializedName
import xyz.mynt.openweather.core.utils.toFormattedDateAndTime
import xyz.mynt.openweather.home.domain.model.Weather

data class WeatherDto(
    val id: Int,
    @SerializedName("name")
    val locationName: String,
    @SerializedName("dt")
    val timestamp: Long,
    @SerializedName("weather")
    val forecast: List<ForecastDto>,
    @SerializedName("sys")
    val system: SystemDto,
    @SerializedName("main")
    val temperature: TemperatureDto,
    @SerializedName("wind")
    val wind: WindDto
) {
    fun toDomain() = Weather(
        id = id,
        locationName = locationName,
        timestamp = timestamp.toFormattedDateAndTime(),
        forecast = forecast.first().toDomain(),
        system = system.toDomain(),
        temperature = temperature.toDomain(),
        wind = wind.toDomain(),
        pk = System.currentTimeMillis(),
    )
}