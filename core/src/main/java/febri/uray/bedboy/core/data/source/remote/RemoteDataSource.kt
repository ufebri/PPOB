package febri.uray.bedboy.core.data.source.remote

import android.util.Log
import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.source.remote.network.ApiResponse
import febri.uray.bedboy.core.data.source.remote.network.ApiService
import febri.uray.bedboy.core.data.source.remote.response.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCheckBalance(requestBody: JsonObject): Flow<ApiResponse<ResponseData>> {
        //get data from remote api
        return flow {
            val response = apiService.getCheckBalance(requestBody)
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
}

