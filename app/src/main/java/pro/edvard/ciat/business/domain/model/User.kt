package pro.edvard.ciat.business.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "users")
data class User(

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String,

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    var firstName: String,

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    var lastName: String,

    @SerializedName("avatar")
    @ColumnInfo(name = "avatar")
    var avatar: String

): Parcelable {

    fun getFullName(): String {
        return "$firstName $lastName".capitalize(Locale.ROOT)
    }

}