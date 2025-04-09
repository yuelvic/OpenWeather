package xyz.mynt.openweather.history.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.mynt.openweather.history.presentation.screen.components.WeatherHistoryCard
import xyz.mynt.openweather.history.presentation.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel = hiltViewModel()) {
    val state by historyViewModel.state.collectAsState()

    when (state) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val weatherHistory = (state as UiState.Success).weatherHistory

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(weatherHistory) { weatherHistory ->
                        WeatherHistoryCard(
                            weatherHistory = weatherHistory
                        )
                    }
                }
            }
        }

        is UiState.Error -> {
            val error = (state as UiState.Error).message

            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("❌ Error: $error", color = Color.Red)
            }
        }
    }
}
