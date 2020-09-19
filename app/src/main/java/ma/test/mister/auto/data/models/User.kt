package ma.test.mister.auto.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "users_table")
data class UserItem(
    @SerializedName("id")
    @PrimaryKey @ColumnInfo(name = "id")
    var id: Int,
    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,
    @SerializedName("username")
    @ColumnInfo(name = "username")
    val username: String
)