package xyz.mynt.openweather.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.mynt.openweather.core.data.local.AppDatabase
import xyz.mynt.openweather.core.data.local.dao.WeatherDao
import xyz.mynt.openweather.core.utils.Constants
import xyz.mynt.openweather.history.data.repository.WeatherHistoryRepositoryImpl
import xyz.mynt.openweather.history.domain.repository.WeatherHistoryRepository
import xyz.mynt.openweather.history.domain.usecase.GetWeatherHistoryUseCase
import xyz.mynt.openweather.home.data.local.LocationService
import xyz.mynt.openweather.home.data.remote.ApiService
import xyz.mynt.openweather.home.data.repository.LocationRepositoryImpl
import xyz.mynt.openweather.home.data.repository.WeatherRepositoryImpl
import xyz.mynt.openweather.home.domain.repository.LocationRepository
import xyz.mynt.openweather.home.domain.repository.WeatherRepository
import xyz.mynt.openweather.home.domain.usecase.GetCurrentLocationUseCase
import xyz.mynt.openweather.home.domain.usecase.GetCurrentLocationWeatherUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideLocationService(
        @ApplicationContext context: Context
    ): LocationService = LocationService(context = context)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            "app_database"
        ).build()

    @Provides
    fun provideWeatherDao(database: AppDatabase): WeatherDao = database.weatherDao()

    @Provides
    @Singleton
    fun provideWeatherRepository(
        apiService: ApiService,
        weatherDao: WeatherDao
    ): WeatherRepository = WeatherRepositoryImpl(
        apiService = apiService,
        weatherDao = weatherDao
    )

    @Provides
    @Singleton
    fun provideWeatherHistoryRepository(
        weatherDao: WeatherDao
    ): WeatherHistoryRepository = WeatherHistoryRepositoryImpl(
        weatherDao = weatherDao
    )

    @Provides
    @Singleton
    fun provideLocationRepository(
        locationService: LocationService
    ): LocationRepository = LocationRepositoryImpl(
        locationService = locationService
    )

    @Provides
    @Singleton
    fun provideGetCurrentLocationWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetCurrentLocationWeatherUseCase = GetCurrentLocationWeatherUseCase(
        weatherRepository = weatherRepository
    )

    @Provides
    @Singleton
    fun provideGetWeatherHistoryUseCase(
        weatherHistoryRepository: WeatherHistoryRepository
    ): GetWeatherHistoryUseCase = GetWeatherHistoryUseCase(
        weatherHistoryRepository = weatherHistoryRepository
    )

    @Provides
    @Singleton
    fun provideGetCurrentLocationUseCase(
        locationRepository: LocationRepository
    ): GetCurrentLocationUseCase = GetCurrentLocationUseCase(
        locationRepository = locationRepository
    )
}