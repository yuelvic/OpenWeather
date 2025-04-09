package xyz.mynt.openweather.home.domain.model

data class Temperature(
    val current : String,
    val feelsLike : String,
    val min : String,
    val max : String,
    val humidity : String
)
