package xyz.mynt.openweather.history.domain.repository

import xyz.mynt.openweather.history.domain.model.WeatherHistory

interface WeatherHistoryRepository {
    suspend fun getWeatherHistory(): List<WeatherHistory>
}