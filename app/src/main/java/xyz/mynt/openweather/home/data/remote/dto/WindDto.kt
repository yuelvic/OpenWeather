package xyz.mynt.openweather.home.data.remote.dto

import xyz.mynt.openweather.home.domain.model.Wind

data class WindDto(
    val speed : Double
) {
    fun toDomain() = Wind(
        speed = speed
    )
}
