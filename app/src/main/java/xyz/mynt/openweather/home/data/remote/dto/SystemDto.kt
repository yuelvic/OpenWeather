package xyz.mynt.openweather.home.data.remote.dto

import xyz.mynt.openweather.core.utils.toFormattedTime
import xyz.mynt.openweather.home.domain.model.System

data class SystemDto(
    val sunrise : Long,
    val sunset : Long
) {
    fun toDomain() = System(
        sunrise = sunrise.toFormattedTime(),
        sunset = sunset.toFormattedTime()
    )
}
