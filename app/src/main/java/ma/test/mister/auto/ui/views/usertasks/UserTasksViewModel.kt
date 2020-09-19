package ma.test.mister.auto.ui.views.usertasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ma.test.mister.auto.data.database.UserTaskDataBase
import ma.test.mister.auto.data.models.TaskItem
import ma.test.mister.auto.data.models.UserItem
import ma.test.mister.auto.data.repositories.TasksRepository
import ma.test.mister.auto.data.repositories.UsersRepository

class UserTasksViewModel(application: Application): AndroidViewModel(application) {
    private var  repository: TasksRepository

    init {
        val taskDao = UserTaskDataBase.getDataBase(application).taskDao()
        repository = TasksRepository(taskDao)
    }

    fun insert(tasks: List<TaskItem>) =
        viewModelScope.launch(Dispatchers.IO){
            repository.insertTask(tasks)
        }

    fun getTasksByUserId(userId: Int) = repository.getTasksByUserId(userId)
}