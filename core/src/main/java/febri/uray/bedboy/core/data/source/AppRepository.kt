package febri.uray.bedboy.core.data.source

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.NetworkBoundResource
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.data.source.local.LocalDataSource
import febri.uray.bedboy.core.data.source.remote.RemoteDataSource
import febri.uray.bedboy.core.data.source.remote.network.ApiResponse
import febri.uray.bedboy.core.data.source.remote.response.ResponseData
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.core.domain.repository.IAppRepository
import febri.uray.bedboy.core.util.AppExecutors
import febri.uray.bedboy.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    override fun getPriceList(): Flow<Resource<List<ProductList>>> =
        object : NetworkBoundResource<List<ProductList>, ResponseData>() {
            override fun loadFromDB(): Flow<List<ProductList>> {
                return localDataSource.getAllPriceList().map {
                    DataMapper.mapEntitiesToDomainPriceList(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseData>> =
                remoteDataSource.getPriceList()

            override suspend fun saveCallResult(data: ResponseData) {
                val response = DataMapper.mapResponseToEntitiesPriceList(data)
                appExecutors.diskIO().execute { localDataSource.insertPriceList(response) }
            }

            override fun shouldFetch(data: List<ProductList>?): Boolean = data.isNullOrEmpty()
        }.asFlow()

    override fun getMenuList(): Flow<List<MenuList>> {
        return localDataSource.getListMenu().map { DataMapper.mapEntitiesToDomainMenu(it) }
    }

    override fun getCallPackageList(provider: String): Flow<List<ProductList>> {
        return localDataSource.getProductList(provider, "bicara")
            .map { DataMapper.mapEntitiesToDomainPriceList(it) }
    }
}