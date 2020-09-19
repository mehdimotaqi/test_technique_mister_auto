package ma.test.mister.auto.data.repositories

import androidx.lifecycle.LiveData
import ma.test.mister.auto.data.dao.TaskDao
import ma.test.mister.auto.data.models.TaskItem

class TasksRepository(private val taskDao: TaskDao) {

    fun getTasksByUserId(userId: Int): LiveData<List<TaskItem>>{
        return taskDao.getTaskByUserId(userId)
    }

    suspend fun insertTask(tasks: List<TaskItem>){
        taskDao.insertTasks(tasks)
    }
}