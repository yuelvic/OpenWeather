package xyz.mynt.openweather.home.domain.usecase

import xyz.mynt.openweather.home.domain.model.Weather
import xyz.mynt.openweather.home.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentLocationWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double,
        token: String
    ): Weather = weatherRepository.getCurrentLocationWeather(
        lat = lat,
        lon = lon,
        token = token
    )
}
