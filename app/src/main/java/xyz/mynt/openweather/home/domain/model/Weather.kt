package xyz.mynt.openweather.home.domain.model

data class Weather(
    val id: Int,
    val locationName: String,
    val timestamp: Int,
    val forecast: Forecast,
    val system: System,
    val temperature: Temperature,
    val wind: Wind
)
