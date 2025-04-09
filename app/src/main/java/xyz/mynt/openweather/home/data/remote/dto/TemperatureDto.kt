package xyz.mynt.openweather.home.data.remote.dto

import com.google.gson.annotations.SerializedName
import xyz.mynt.openweather.home.domain.model.Temperature

data class TemperatureDto(
    @SerializedName("temp")
    val current : Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val min : Double,
    @SerializedName("temp_max")
    val max : Double,
    val humidity : Double
) {
    fun toDomain() = Temperature(
        current = "${current.toInt()}°C",
        feelsLike = "${feelsLike.toInt()}°C",
        min = "${min.toInt()}°C",
        max = "${max.toInt()}°C",
        humidity = "${humidity.toInt()}%",
    )
}
