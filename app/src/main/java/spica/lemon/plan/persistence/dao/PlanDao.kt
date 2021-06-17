@file:Suppress("unused")
package spica.lemon.plan.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import spica.lemon.plan.model.Plan

@Dao
interface PlanDao {

    @Insert
    suspend fun insertPlan(plan: Plan)


    @Delete
    suspend fun deletePlan(plan: Plan)


    @Query("SELECT * FROM `plan` ORDER BY lv ")
    suspend fun getAllPLan(): LiveData<List<Plan>>


}