package xyz.mynt.openweather.home.domain.usecase

import xyz.mynt.openweather.home.domain.model.CurrentLocation
import xyz.mynt.openweather.home.domain.repository.LocationRepository
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): CurrentLocation =
        locationRepository.getCurrentLocation()
}