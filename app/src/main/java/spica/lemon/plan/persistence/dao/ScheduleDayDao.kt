@file:Suppress("unused")
package spica.lemon.plan.persistence.dao

import androidx.room.*
import spica.lemon.plan.model.Schedule
import spica.lemon.plan.model.ScheduleDay
import spica.lemon.plan.model.ScheduleDayWithSchedule

@Dao
interface ScheduleDayDao {

    @Insert
    suspend fun insertSchedule(scheduleDay: ScheduleDay)

    @Delete
    suspend fun deleteSchedule(scheduleDay: ScheduleDay)


    @Query("DELETE FROM  table_day")
    suspend fun deleteAll()


    @Transaction
    @Query("SELECT * FROM table_day")
    suspend fun getDayWithSchedules(): List<ScheduleDayWithSchedule>


    @Transaction
    @Query("SELECT * FROM table_day WHERE id = :id")
    suspend fun getDayWithScheduleById(id: Long): Schedule

    @Transaction
    @Query("SELECT * FROM table_day WHERE createDate = :date")
    suspend fun getDayWithSchedule(date: String): List<ScheduleDayWithSchedule>

}