package xyz.mynt.openweather.home.data.local.dto

import xyz.mynt.openweather.home.domain.model.CurrentLocation

data class CurrentLocationDto(
    val lat: Double,
    val lon: Double
) {
    fun toDomain() = CurrentLocation(
        lat = lat,
        lon = lon
    )
}
