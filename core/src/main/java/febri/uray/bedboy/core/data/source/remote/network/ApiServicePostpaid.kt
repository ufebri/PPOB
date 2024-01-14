package febri.uray.bedboy.core.data.source.remote.network

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.source.remote.response.ResponseList
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServicePostpaid {

    /* Postpaid */
    @Headers("Content-Type: application/json")
    @POST("/api/v1/bill/check")
    suspend fun getPriceListPostPaid(@Body requestBody: JsonObject): ResponseList
}