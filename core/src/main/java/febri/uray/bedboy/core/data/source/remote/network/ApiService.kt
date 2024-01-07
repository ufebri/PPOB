package febri.uray.bedboy.core.data.source.remote.network

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.source.remote.response.ResponseList
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/check-balance")
    suspend fun getCheckBalance(@Body requestBody: JsonObject): ResponseList

    @POST("/api/pricelist")
    suspend fun getPriceList(@Body requestBody: JsonObject): ResponseList

    @POST("/api/top-up")
    suspend fun getTopUp(@Body requestBody: JsonObject): ResponseList

    @POST("/api/check-status")
    suspend fun getCheckStatus(@Body requestBody: JsonObject): ResponseList
}
