package febri.uray.bedboy.core.domain.usecase

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppInteractor @Inject constructor(private var appRepository: IAppRepository) : AppUseCase {

    override fun getBalance(request: JsonObject): Flow<Resource<Balance>> =
        appRepository.getCheckBalance(request)


}