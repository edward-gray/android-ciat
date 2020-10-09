package pro.edvard.ciat.framework.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_remote_keys")
data class UserRemoteKeys (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    var userId: Int,

    val prevKey: Int?,

    val nextKey: Int?
)