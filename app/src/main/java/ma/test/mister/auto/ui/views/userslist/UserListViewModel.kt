package ma.test.mister.auto.ui.views.userslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ma.test.mister.auto.data.database.UserTaskDataBase
import ma.test.mister.auto.data.models.UserItem
import ma.test.mister.auto.data.repositories.UsersRepository

class UserListViewModel(application: Application): AndroidViewModel(application) {

    private var  repository: UsersRepository
    var allUsers: LiveData<List<UserItem>>? = null

    init {
        val userDao = UserTaskDataBase.getDataBase(application).usersDao()
        repository = UsersRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insert(users: List<UserItem>) =
        viewModelScope.launch(Dispatchers.IO){
            repository.insertUsers(users)
        }
}