package xyz.mynt.openweather.home.presentation.screen

import xyz.mynt.openweather.home.domain.model.Weather

sealed class UiState {
    data object Loading: UiState()
    data class Success(val weather: Weather): UiState()
    data class Error(val message: String): UiState()
}