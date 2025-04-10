package xyz.mynt.openweather.home.data.repository

import xyz.mynt.openweather.home.data.local.LocationService
import xyz.mynt.openweather.home.domain.model.CurrentLocation
import xyz.mynt.openweather.home.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationService: LocationService
) : LocationRepository {
    override suspend fun getCurrentLocation(): CurrentLocation {
        return locationService.getCurrentLocation().toDomain()
    }
}