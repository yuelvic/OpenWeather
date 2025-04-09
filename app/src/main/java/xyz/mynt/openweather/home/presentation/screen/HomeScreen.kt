package xyz.mynt.openweather.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import xyz.mynt.openweather.home.presentation.screen.components.WeatherInfoCard
import xyz.mynt.openweather.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val state by homeViewModel.state.collectAsState()

    when (state) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val weather = (state as UiState.Success).weather

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        weather.locationName,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text("Feels like ${weather.temperature.feelsLike}")
                    AsyncImage(
                        model = weather.forecast.icon,
                        contentDescription = null,
                    )
                    Text(
                        weather.temperature.current,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        WeatherInfoCard(
                            modifier = Modifier.weight(1f),
                            label = "Sunrise",
                            value = weather.system.sunrise
                        )
                        WeatherInfoCard(
                            modifier = Modifier.weight(1f),
                            label = "Sunset",
                            value = weather.system.sunset
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
