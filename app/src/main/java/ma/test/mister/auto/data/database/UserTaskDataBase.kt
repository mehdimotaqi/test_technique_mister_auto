package ma.test.mister.auto.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ma.test.mister.auto.data.dao.TaskDao
import ma.test.mister.auto.data.dao.UsersDao
import ma.test.mister.auto.data.models.TaskItem
import ma.test.mister.auto.data.models.UserItem

@Database(entities = [UserItem::class, TaskItem::class], version = 2, exportSchema = false)
public abstract class UserTaskDataBase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var INSTANCE: UserTaskDataBase? = null

        fun getDataBase(context: Context): UserTaskDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserTaskDataBase::class.java,
                    "users_tasks_data_base"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}