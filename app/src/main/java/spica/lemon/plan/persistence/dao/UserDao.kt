package spica.lemon.plan.persistence.dao

import androidx.room.*
import spica.lemon.plan.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)


    @Query("SELECT * FROM user WHERE  id = 1")
    suspend fun queryUser(): User?

    @Query("DELETE FROM user WHERE id = 1")
    suspend fun deleteUser()


}