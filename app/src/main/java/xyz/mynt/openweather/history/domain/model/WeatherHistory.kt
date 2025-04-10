package xyz.mynt.openweather.history.domain.model

data class WeatherHistory(
    val id: Int,
    val locationName: String,
    val temperature: String,
    val description: String,
    val timestamp: String
)
