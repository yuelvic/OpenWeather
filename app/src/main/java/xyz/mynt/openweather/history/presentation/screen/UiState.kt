package xyz.mynt.openweather.history.presentation.screen

import xyz.mynt.openweather.history.domain.model.WeatherHistory

sealed class UiState {
    data object Loading: UiState()
    data class Success(val weatherHistory: List<WeatherHistory>): UiState()
    data class Error(val message: String): UiState()
}