package febri.uray.bedboy.core.data.source

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.data.source.local.LocalDataSource
import febri.uray.bedboy.core.data.source.remote.RemoteDataSource
import febri.uray.bedboy.core.data.source.remote.network.ApiResponse
import febri.uray.bedboy.core.data.source.remote.response.ResponseData
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.repository.IAppRepository
import febri.uray.bedboy.core.util.AppExecutors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAppRepository {


    override fun getCheckBalance(requestData: JsonObject): Flow<Resource<Balance>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getCheckBalance(requestData).collect { apiResponse ->
            try {
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(mapToBalance(apiResponse.data)))
                    }

                    is ApiResponse.Error -> {
                        emit(Resource.Error<Balance>(apiResponse.errorMessage))
                    }

                    else -> {
                        emit(Resource.Error<Balance>("null", null))
                    }
                }
            } catch (e: Exception) {
                when (apiResponse) {
                    is ApiResponse.Error -> emit(Resource.Error<Balance>(apiResponse.errorMessage))
                    else -> emit(Resource.Error<Balance>(e.message ?: "Error baru"))
                }
            }
        }
    }.catch { e -> emit(Resource.Error<Balance>(e.message ?: "Error Baru")) }

    private fun mapToBalance(responseData: ResponseData): Balance {
        // Implement your mapping logic from ResponseData to Balance
        return Balance(balance = responseData.balance.toString())
    }


}