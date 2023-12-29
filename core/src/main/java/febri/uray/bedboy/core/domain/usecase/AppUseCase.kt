package febri.uray.bedboy.core.domain.usecase

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.Balance
import kotlinx.coroutines.flow.Flow


interface AppUseCase {

    fun getBalance(request: JsonObject): Flow<Resource<Balance>>
}