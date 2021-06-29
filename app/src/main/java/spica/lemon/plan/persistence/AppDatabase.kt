package spica.lemon.plan.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import spica.lemon.plan.model.Schedule
import spica.lemon.plan.model.User
import spica.lemon.plan.persistence.dao.ScheduleDao
import spica.lemon.plan.persistence.dao.UserDao


@Database(entities = [Schedule::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun scheduleDao(): ScheduleDao


    abstract fun userDao():UserDao

}