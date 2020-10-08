package pro.edvard.ciat.business.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(

    @SerializedName("id")
    var id: Int,

    @SerializedName("email")
    var email: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("avatar")
    var avatar: String,

): Parcelable {

    fun getFullName(): String {
        return "$firstName $lastName".capitalize(Locale.ROOT)
    }

}