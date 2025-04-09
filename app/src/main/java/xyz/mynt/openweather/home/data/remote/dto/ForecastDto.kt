package xyz.mynt.openweather.home.data.remote.dto

import com.google.gson.annotations.SerializedName
import xyz.mynt.openweather.core.utils.Constants
import xyz.mynt.openweather.home.domain.model.Forecast

data class ForecastDto(
    @SerializedName("main")
    val status : String,
    val icon : String
) {
    fun toDomain() = Forecast(
        status = status,
        icon = Constants.AVATAR_URL_PREFIX + icon + Constants.AVATAR_URL_SUFFIX
    )
}
