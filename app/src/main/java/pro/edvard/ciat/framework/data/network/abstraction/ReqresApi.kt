package pro.edvard.ciat.framework.data.network.abstraction

import pro.edvard.ciat.framework.data.network.model.ReqresNetwork
import pro.edvard.ciat.framework.data.network.model.UserNetwork
import pro.edvard.ciat.framework.data.network.util.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresApi {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): NetworkResponse<ReqresNetwork, Error>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): NetworkResponse<UserNetwork, Error>

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }

}