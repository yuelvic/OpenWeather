package xyz.mynt.openweather.history.domain.usecase

import xyz.mynt.openweather.history.domain.model.WeatherHistory
import xyz.mynt.openweather.history.domain.repository.WeatherHistoryRepository
import javax.inject.Inject

class GetWeatherHistoryUseCase @Inject constructor(
    private val weatherHistoryRepository: WeatherHistoryRepository
) {
    suspend operator fun invoke(): List<WeatherHistory> =
        weatherHistoryRepository.getWeatherHistory()
}