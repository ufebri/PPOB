package febri.uray.bedboy.core.data.source.remote

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import febri.uray.bedboy.core.data.source.remote.network.ApiResponse
import febri.uray.bedboy.core.data.source.remote.network.ApiServicePostpaid
import febri.uray.bedboy.core.data.source.remote.network.ApiServicePrepaid
import febri.uray.bedboy.core.data.source.remote.response.ResponseData
import febri.uray.bedboy.core.security.MD5Helper
import febri.uray.bedboy.core.util.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiServicePrepaid: ApiServicePrepaid,
    private val apiServicePostpaid: ApiServicePostpaid,
    @ApplicationContext private val context: Context
) {
    val TAG = "RemoteDataSource"

    /* Prepaid */
    suspend fun getCheckBalance(requestBody: JsonObject): Flow<ApiResponse<ResponseData>> {
        //get data from remote api
        return flow {
            val response = apiServicePrepaid.getCheckBalance(requestBody)
            val dataArray = response.data
            try {
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(dataArray?.message ?: e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    suspend fun getPriceListPrepaid(): Flow<ApiResponse<ResponseData>> {
        //get data from remote api
        return flow {
            try {
                val mSharedPreferencesHelper = SharedPreferencesHelper(context)
                val username = mSharedPreferencesHelper.getString("username")
                val key = mSharedPreferencesHelper.getString("apikey")
                val sign = MD5Helper.calculateMD5(String.format("%s%spl", username, key))
                val requestBody = JsonObject().apply {
                    addProperty("status", "active")
                    addProperty("username", username)
                    addProperty("sign", sign)
                }

                Log.d(
                    TAG,
                    String.format("username : %s \n key : %s \n sign: %s", username, key, sign)
                )

                val response = apiServicePrepaid.getPriceListPrepaid(requestBody)
                val dataArray = response.data
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopUp(productCode: String, customerID: String): Flow<ApiResponse<ResponseData>> {
        //get data from remote api
        return flow {
            val mSharedPreferencesHelper = SharedPreferencesHelper(context)
            val username = mSharedPreferencesHelper.getString("username")
            val key = mSharedPreferencesHelper.getString("apikey")
            val refID = String.format("order%s", 10000000 + Random.nextInt(90000000))
            val sign = MD5Helper.calculateMD5(String.format("%s%s%s", username, key, refID))

            val requestBodyTopup = JsonObject().apply {
                addProperty("ref_id", refID)
                addProperty("product_code", productCode)
                addProperty("customer_id", customerID)
                addProperty("username", username)
                addProperty("sign", sign)
            }

            Log.d(
                TAG,
                String.format("username : %s \n key : %s \n sign: %s", username, key, sign)
            )


            var response = apiServicePrepaid.getTopUp(requestBodyTopup)

            //Doing Auto Check Status if the status on process
            if (response.data?.status.equals("0")) {
                val requestBodyCheckStatus = JsonObject().apply {
                    addProperty("ref_id", refID)
                    addProperty("username", username)
                    addProperty("sign", sign)
                }

                response = apiServicePrepaid.getCheckStatus(requestBodyCheckStatus)
            }

            val dataArray = response.data
            try {
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(dataArray?.message ?: e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    /* Postpaid */
    suspend fun getPriceListPostpaid(): Flow<ApiResponse<ResponseData>> {
        return flow<ApiResponse<ResponseData>> {
            try {
                val mSharedPreferencesHelper = SharedPreferencesHelper(context)
                val username = mSharedPreferencesHelper.getString("username")
                val key = mSharedPreferencesHelper.getString("apikey")
                val sign = MD5Helper.calculateMD5(String.format("%s%spl", username, key))
                val requestBody = JsonObject().apply {
                    addProperty("commands", "pricelist-pasca")
                    addProperty("status", "active")
                    addProperty("username", username)
                    addProperty("sign", sign)
                }

                Log.d(
                    TAG,
                    String.format("username : %s \n key : %s \n sign: %s", username, key, sign)
                )

                val response = apiServicePostpaid.getPriceListPostPaid(requestBody)
                val dataArray = response.data
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

