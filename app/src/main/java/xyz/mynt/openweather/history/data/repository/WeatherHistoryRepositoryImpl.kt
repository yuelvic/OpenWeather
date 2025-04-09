package xyz.mynt.openweather.history.data.repository

import xyz.mynt.openweather.core.data.local.dao.WeatherDao
import xyz.mynt.openweather.history.domain.model.WeatherHistory
import xyz.mynt.openweather.history.domain.repository.WeatherHistoryRepository

class WeatherHistoryRepositoryImpl(
    private val weatherDao: WeatherDao
) : WeatherHistoryRepository {
    override suspend fun getWeatherHistory(): List<WeatherHistory> {
        return weatherDao.getAll().map { it.toDomain() }
    }
}
