package xyz.mynt.openweather.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.mynt.openweather.BuildConfig
import xyz.mynt.openweather.home.domain.usecase.GetCurrentLocationUseCase
import xyz.mynt.openweather.home.domain.usecase.GetCurrentLocationWeatherUseCase
import xyz.mynt.openweather.home.presentation.screen.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getCurrentLocationWeatherUseCase: GetCurrentLocationWeatherUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state
    private var hasRequestedWeather = false

    fun getCurrentLocationWeather() {
        if (hasRequestedWeather) return

        hasRequestedWeather = true

        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val currentLocation = getCurrentLocationUseCase()

                val weather = getCurrentLocationWeatherUseCase(
                    lat = currentLocation.lat,
                    lon = currentLocation.lon,
                    token = BuildConfig.OPEN_WEATHER_TOKEN
                )
                _state.value = UiState.Success(weather)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.localizedMessage ?: "Something went wrong")
            }
        }
    }
}
