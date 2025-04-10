package xyz.mynt.openweather.home.data.repository

import xyz.mynt.openweather.core.data.local.dao.WeatherDao
import xyz.mynt.openweather.core.data.local.entity.toEntity
import xyz.mynt.openweather.home.data.remote.ApiService
import xyz.mynt.openweather.home.domain.model.Weather
import xyz.mynt.openweather.home.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override suspend fun getCurrentLocationWeather(
        lat: Double,
        lon: Double,
        token: String
    ): Weather {
        val weather = apiService.getCurrentLocationWeather(
            lat = lat,
            lon = lon,
            token = token
        ).toDomain()

        // Save to local DB
        weatherDao.insertWithLimit(weather.toEntity())

        return weather
    }
}
