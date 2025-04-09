package xyz.mynt.openweather.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.mynt.openweather.core.data.local.dao.WeatherDao
import xyz.mynt.openweather.core.data.local.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
