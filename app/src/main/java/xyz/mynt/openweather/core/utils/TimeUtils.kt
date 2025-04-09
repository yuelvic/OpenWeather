package xyz.mynt.openweather.core.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val formatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault())

fun Long.toFormattedTime(): String {
    val instant = Instant.ofEpochSecond(this)
    val zoned = instant.atZone(ZoneId.systemDefault())
    return formatter.format(zoned)
}