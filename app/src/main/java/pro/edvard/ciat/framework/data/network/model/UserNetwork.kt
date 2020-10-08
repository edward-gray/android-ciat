package pro.edvard.ciat.framework.data.network.model

import com.google.gson.annotations.SerializedName
import pro.edvard.ciat.business.domain.model.User

data class UserNetwork(
    @SerializedName("data")
    var user: User
)