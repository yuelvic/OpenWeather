package xyz.mynt.openweather.history.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.mynt.openweather.history.domain.usecase.GetWeatherHistoryUseCase
import xyz.mynt.openweather.history.presentation.screen.UiState
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getWeatherHistoryUseCase: GetWeatherHistoryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    init {
        getWeatherHistory()
    }

    private fun getWeatherHistory() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val weatherHistory = getWeatherHistoryUseCase()
                println(weatherHistory)
                _state.value = UiState.Success(
                    weatherHistory = weatherHistory
                )
            } catch (e: Exception) {
                _state.value = UiState.Error(e.localizedMessage ?: "Something went wrong")
            }
        }
    }
}