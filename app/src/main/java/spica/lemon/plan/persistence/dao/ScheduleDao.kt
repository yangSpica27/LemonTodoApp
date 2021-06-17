@file:Suppress("unused")
package spica.lemon.plan.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import spica.lemon.plan.model.Schedule

@Dao
interface ScheduleDao {

    @Insert
    suspend fun insertSchedule(schedule: Schedule)

    @Delete
    suspend fun deleteSchedule(schedule: Schedule)


    @Query("DELETE FROM  schedule")
    suspend fun deleteAll()


}