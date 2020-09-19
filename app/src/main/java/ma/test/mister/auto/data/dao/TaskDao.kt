package ma.test.mister.auto.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ma.test.mister.auto.data.models.TaskItem

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks_table WHERE userId = :userId")
    fun getTaskByUserId(userId: Int): LiveData<List<TaskItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskItem>)
}