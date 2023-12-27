package febri.uray.bedboy.core.domain.repository

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.Balance
import kotlinx.coroutines.flow.Flow


interface IAppRepository {

    fun getCheckBalance(requestData: JsonObject): Flow<Resource<Balance>>
}