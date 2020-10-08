package pro.edvard.ciat.framework.data.network.model

import com.google.gson.annotations.SerializedName
import pro.edvard.ciat.business.domain.model.User

data class ReqresNetwork(
    @SerializedName("page")
    var page: Int,

    @SerializedName("per_page")
    var perPage: Int,

    @SerializedName("total_page")
    var totalPages: Int,

    @SerializedName("data")
    var users: List<User>
) {
}