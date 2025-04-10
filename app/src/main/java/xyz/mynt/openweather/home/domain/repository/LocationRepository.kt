package xyz.mynt.openweather.home.domain.repository

import xyz.mynt.openweather.home.domain.model.CurrentLocation

interface LocationRepository {
    suspend fun getCurrentLocation(): CurrentLocation
}