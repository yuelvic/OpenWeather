package xyz.mynt.openweather.home.domain.model

data class Weather(
    val pk: Long,
    val id: Int,
    val locationName: String,
    val timestamp: String,
    val forecast: Forecast,
    val system: System,
    val temperature: Temperature,
    val wind: Wind
)
