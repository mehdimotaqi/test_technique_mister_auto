package ma.test.mister.auto.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks_table")
data class TaskItem(
    @SerializedName("completed")
    @ColumnInfo(name = "completed")
    val completed: Boolean,
    @SerializedName("id")
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    val userId: Int
)