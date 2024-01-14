package febri.uray.bedboy.core.data.source

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.NetworkBoundResource
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.data.source.local.LocalDataSource
import febri.uray.bedboy.core.data.source.remote.RemoteDataSource
import febri.uray.bedboy.core.data.source.remote.network.ApiResponse
import febri.uray.bedboy.core.data.source.remote.response.ResponseData
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.model.PostpaidProduct
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.core.domain.model.ResultTransaction
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
                        emit(Resource.Success(Balance(balance = apiResponse.data.balance.toString())))
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

    override fun getPriceList(): Flow<Resource<List<ProductList>>> =
        object : NetworkBoundResource<List<ProductList>, ResponseData>() {
            override fun loadFromDB(): Flow<List<ProductList>> {
                return localDataSource.getAllPriceList().map {
                    DataMapper.mapEntitiesToDomainPriceList(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseData>> =
                remoteDataSource.getPriceListPrepaid()

            override suspend fun saveCallResult(data: ResponseData) {
                val response = DataMapper.mapResponseToEntitiesPriceList(data)
                appExecutors.diskIO().execute { localDataSource.insertPriceList(response) }
            }

            override fun shouldFetch(data: List<ProductList>?): Boolean = data.isNullOrEmpty()
        }.asFlow()

    override fun getProductListCategory(productCategory: String): Flow<List<String>> {
        return localDataSource.getProductListCategory(productCategory).map {
            DataMapper.mapEntitiesToDomainMenu(it)
        }
    }

    override fun getProductListDesc(
        productDesc: String,
        productCategory: String
    ): Flow<List<ProductList>> {
        return localDataSource.getProductList(productDesc, productCategory)
            .map { DataMapper.mapEntitiesToDomainPriceList(it) }
    }

    override fun getTopUpResult(
        productCode: String,
        customerID: String
    ): Flow<Resource<ResultTransaction>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getTopUp(productCode, customerID).collect { apiResponse ->
            try {
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(DataMapper.mapResponseToTransactionDomain(apiResponse.data)))
                    }

                    is ApiResponse.Error -> {
                        emit(Resource.Error<ResultTransaction>(apiResponse.errorMessage))
                    }

                    else -> {
                        emit(Resource.Error<ResultTransaction>("null", null))
                    }
                }
            } catch (e: Exception) {
                when (apiResponse) {
                    is ApiResponse.Error -> emit(Resource.Error<ResultTransaction>(apiResponse.errorMessage))
                    else -> emit(Resource.Error<ResultTransaction>(e.message ?: "Error baru"))
                }
            }
        }
    }.catch { e -> emit(Resource.Error<ResultTransaction>(e.message ?: "Error Baru")) }

    override fun getProductListPostpaid(): Flow<Resource<List<PostpaidProduct>>> =
        object : NetworkBoundResource<List<PostpaidProduct>, ResponseData>() {
            override fun loadFromDB(): Flow<List<PostpaidProduct>> =
                localDataSource.getAllProductListPostpaid()
                    .map { DataMapper.mapEntitiesToDomainPostpaidList(it) }

            override suspend fun createCall(): Flow<ApiResponse<ResponseData>> =
                remoteDataSource.getPriceListPostpaid()

            override suspend fun saveCallResult(data: ResponseData) {
                val response = DataMapper.mapResponseToEntitiesPostpaidList(data)
                appExecutors.diskIO().execute { localDataSource.insertPriceListPostPaid(response) }
            }

            override fun shouldFetch(data: List<PostpaidProduct>?): Boolean = data.isNullOrEmpty()

        }.asFlow()
}