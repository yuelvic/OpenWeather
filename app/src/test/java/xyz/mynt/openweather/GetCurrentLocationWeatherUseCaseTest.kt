package xyz.mynt.openweather

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import xyz.mynt.openweather.home.domain.model.Forecast
import xyz.mynt.openweather.home.domain.model.System
import xyz.mynt.openweather.home.domain.model.Temperature
import xyz.mynt.openweather.home.domain.model.Weather
import xyz.mynt.openweather.home.domain.model.Wind
import xyz.mynt.openweather.home.domain.repository.WeatherRepository
import xyz.mynt.openweather.home.domain.usecase.GetCurrentLocationWeatherUseCase
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentLocationWeatherUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getCurrentLocationWeatherUseCase: GetCurrentLocationWeatherUseCase

    private val testWeather = Weather(
        temperature = Temperature(
            "100°C","100°C","100°C","100°C","40%"
        ),
        id = 12345,
        locationName = "Halayhay",
        timestamp = "Apr 10, 2025 • 06:33 PM",
        forecast = Forecast("cloudy", "10d"),
        system = System("10:40 AM", "10:40 PM", "PH"),
        wind = Wind(23.5),
        pk = 1744281468
    )

    @Before
    fun setup() {
        weatherRepository = mockk()
        getCurrentLocationWeatherUseCase = GetCurrentLocationWeatherUseCase(weatherRepository)
    }

    @Test
    fun `invoke should return weather from repository`(): Unit = runTest {
        // Arrange
        val lat = 14.6
        val lon = 120.98
        val token = "e892j23pLh8sA9Bmms9023n0Tyy23"

        coEvery {
            weatherRepository.getCurrentLocationWeather(lat, lon, token)
        } returns testWeather

        // Act
        getCurrentLocationWeatherUseCase(lat, lon, token)

        // Assert
        coVerify(exactly = 1) {
            weatherRepository.getCurrentLocationWeather(lat, lon, token)
        }
    }

    @Test(expected = IOException::class)
    fun `invoke should throw error from repository`() = runTest {
        val lat = 0.0
        val lon = 0.0
        val token = "bad-token"

        coEvery {
            weatherRepository.getCurrentLocationWeather(lat, lon, token)
        } throws IOException("Network error")

        getCurrentLocationWeatherUseCase(lat, lon, token)
    }
}