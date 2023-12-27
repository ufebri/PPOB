package febri.uray.bedboy.core.data

import febri.uray.bedboy.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


abstract class RemoteBoundResource<ResultType, RequestType>(
    private val coroutineScope: CoroutineScope = CoroutineScope(
        Dispatchers.IO
    )
) {

    private val result: MutableStateFlow<Resource<ResultType>> =
        MutableStateFlow(Resource.Loading())

    init {
        fetchData()
    }

    private fun fetchData() {
        coroutineScope.launch {
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    handleSuccess(apiResponse.data)
                }

                is ApiResponse.Empty -> {
                    result.value = Resource.Error("", getEmptyResult())
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.value = Resource.Error<ResultType>(apiResponse.errorMessage)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected open fun getEmptyResult(): ResultType? = null

    protected abstract fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun mapRemoteData(response: RequestType): ResultType

    private suspend fun handleSuccess(data: RequestType) {
        val mappedData = mapRemoteData(data)
        result.value = Resource.Success(mappedData)
    }

    fun asFlow(): Flow<Resource<ResultType>> = result
}
