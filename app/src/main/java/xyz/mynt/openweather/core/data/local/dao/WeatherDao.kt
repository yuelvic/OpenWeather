package xyz.mynt.openweather.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import xyz.mynt.openweather.core.data.local.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    suspend fun getAll(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherEntity)

    @Query("SELECT COUNT(*) FROM weather")
    suspend fun count(): Int

    @Query("DELETE FROM weather WHERE id IN (SELECT id FROM weather ORDER BY timestamp ASC LIMIT :deleteCount)")
    suspend fun deleteOldest(deleteCount: Int)

    @Transaction
    suspend fun insertWithLimit(weather: WeatherEntity) {
        val currentCount = count()
        println("$currentCount")
        if (currentCount >= 10) {
            val toDelete = (currentCount + 1) - 10
            deleteOldest(toDelete)
        }
        insert(weather)
    }
}