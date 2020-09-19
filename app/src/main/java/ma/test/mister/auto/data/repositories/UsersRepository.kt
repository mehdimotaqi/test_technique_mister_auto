package ma.test.mister.auto.data.repositories

import androidx.lifecycle.LiveData
import ma.test.mister.auto.data.dao.UsersDao
import ma.test.mister.auto.data.models.UserItem

class UsersRepository(private val usersDao: UsersDao) {

    val allUsers :LiveData<List<UserItem>>  = usersDao.getAllUsers()

    suspend fun insertUsers(users: List<UserItem>){
        usersDao.insert(users)
    }
}