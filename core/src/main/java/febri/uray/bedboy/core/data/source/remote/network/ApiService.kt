package febri.uray.bedboy.core.data.source.remote.network

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.source.remote.response.ResponseList
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/check-balance")
    suspend fun getCheckBalance(@Body requestBody: JsonObject): ResponseList
}
