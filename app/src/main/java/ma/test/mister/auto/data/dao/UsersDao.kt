package ma.test.mister.auto.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import ma.test.mister.auto.data.models.UserItem

@Dao
interface UsersDao {

    @Query("SELECT * from users_table")
    fun getAllUsers(): LiveData<List<UserItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<UserItem>)
}