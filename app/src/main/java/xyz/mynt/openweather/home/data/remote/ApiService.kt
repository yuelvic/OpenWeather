package xyz.mynt.openweather.home.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import xyz.mynt.openweather.home.data.remote.dto.WeatherDto

interface ApiService {

    @GET("weather")
    suspend fun getCurrentLocationWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("units") units : String = "metric",
        @Query("appid") token : String
    ) : WeatherDto
}
