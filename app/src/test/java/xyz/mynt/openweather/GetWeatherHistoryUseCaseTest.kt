package xyz.mynt.openweather

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import xyz.mynt.openweather.history.domain.model.WeatherHistory
import xyz.mynt.openweather.history.domain.repository.WeatherHistoryRepository
import xyz.mynt.openweather.history.domain.usecase.GetWeatherHistoryUseCase
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherHistoryUseCaseTest {

    private lateinit var weatherHistoryRepository: WeatherHistoryRepository
    private lateinit var getWeatherHistoryUseCase: GetWeatherHistoryUseCase

    private val testWeather = listOf(
        WeatherHistory(
            temperature = "100°C",
            id = 12345,
            locationName = "Halayhay",
            timestamp = "Apr 10, 2025 • 06:33 PM",
            description = "cloudy"
        )
    )

    @Before
    fun setup() {
        weatherHistoryRepository = mockk()
        getWeatherHistoryUseCase = GetWeatherHistoryUseCase(weatherHistoryRepository)
    }

    @Test
    fun `invoke should return weather history list from repository`(): Unit = runTest {
        coEvery {
            weatherHistoryRepository.getWeatherHistory()
        } returns testWeather

        // Act
        getWeatherHistoryUseCase()

        // Assert
        coVerify(exactly = 1) {
            weatherHistoryRepository.getWeatherHistory()
        }
    }

    @Test(expected = IOException::class)
    fun `invoke should throw error from repository`() = runTest {
        coEvery {
            weatherHistoryRepository.getWeatherHistory()
        } throws IOException("Database error")
        getWeatherHistoryUseCase()
    }
}