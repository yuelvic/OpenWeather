package xyz.mynt.openweather.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import xyz.mynt.openweather.home.presentation.screen.components.WeatherInfoCard
import xyz.mynt.openweather.home.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val state by homeViewModel.state.collectAsState()
    val locationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(locationPermissionState.status) {
        if (locationPermissionState.status.isGranted) {
            homeViewModel.getCurrentLocationWeather()
        }
    }

    when {
        locationPermissionState.status.isGranted -> Unit
        locationPermissionState.status.shouldShowRationale -> {
            Text("We need your location to show weather info.")
        }
        else -> {
            Text("Permission not granted. Please allow in settings.")
        }
    }

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
                        "${weather.locationName}, ${weather.system.country}",
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        weather.timestamp,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        "${weather.forecast.status} • Feels like ${weather.temperature.feelsLike}",
                        style = MaterialTheme.typography.labelLarge
                    )
                    AsyncImage(
                        model = weather.forecast.icon,
                        contentDescription = null,
                    )
                    Text(
                        weather.temperature.current,
                        style = MaterialTheme.typography.displayMedium
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
