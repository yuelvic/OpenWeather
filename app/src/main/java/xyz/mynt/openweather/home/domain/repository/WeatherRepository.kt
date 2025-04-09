package xyz.mynt.openweather.home.domain.repository

import xyz.mynt.openweather.home.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentLocationWeather(
        lat: Double,
        lon: Double,
        token: String
    ): Weather
}