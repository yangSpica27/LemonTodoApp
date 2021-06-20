@file:Suppress("unused")
package spica.lemon.plan.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import spica.lemon.plan.model.Schedule

@Dao
interface PlanDao {

    @Insert
    suspend fun insertPlan(schedule: Schedule)


    @Delete
    suspend fun deletePlan(schedule: Schedule)


    @Query("SELECT * FROM `plan` ORDER BY lv ")
    suspend fun getAllPLan(): LiveData<List<Schedule>>


}