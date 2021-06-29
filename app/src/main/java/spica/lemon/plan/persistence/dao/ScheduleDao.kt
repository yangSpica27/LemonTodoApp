package spica.lemon.plan.persistence.dao

import androidx.room.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import spica.lemon.plan.model.Schedule
import spica.lemon.plan.persistence.ChildScheduleConverts

@Dao
@TypeConverters(ChildScheduleConverts::class)
interface ScheduleDao {


    @Insert
    suspend fun insertSchedule(schedule: Schedule)


    @Delete
    suspend fun deleteSchedule(schedule: Schedule)

    @Update
    suspend fun updateSchedule(schedule: Schedule)

    @Insert
    suspend fun insertAll(list: List<Schedule>)


    @Query("DELETE FROM schedule")
    suspend fun deleteAll()

//
//    @Query("SELECT * FROM schedule WHERE date = :date")
//    fun getScheduleByDate(date: String): Flow<List<Schedule>>
//
//
//    @ExperimentalCoroutinesApi
//    fun getAllUntilChanged(date: String) = getScheduleByDate(date).distinctUntilChanged()


}