package xyz.mynt.openweather.home.data.local

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import xyz.mynt.openweather.home.data.local.dto.CurrentLocationDto
import java.util.concurrent.CancellationException
import javax.inject.Inject

class LocationService @Inject constructor(
    private val context: Context
) {
    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): CurrentLocationDto = suspendCancellableCoroutine { cont ->
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    cont.resume(
                        CurrentLocationDto(
                            lat = location.latitude,
                            lon = location.longitude
                        ),
                        onCancellation = null
                    )
                } else {
                    cont.cancel(CancellationException("Location is null"))
                }
            }
            .addOnFailureListener { exception ->
                cont.cancel(exception)
            }
    }
}