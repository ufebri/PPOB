package febri.uray.bedboy.core.domain.usecase

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.core.domain.model.ResultTransaction
import febri.uray.bedboy.core.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppInteractor @Inject constructor(private var appRepository: IAppRepository) : AppUseCase {

    override fun getBalance(request: JsonObject): Flow<Resource<Balance>> =
        appRepository.getCheckBalance(request)

    override fun getPriceList(): Flow<Resource<List<ProductList>>> = appRepository.getPriceList()
    override fun getMenuList(): Flow<List<MenuList>> = appRepository.getMenuList()
    override fun getCallPackageList(provider: String): Flow<List<ProductList>> =
        appRepository.getCallPackageList(provider)

    override fun getTopUpDataResult(
        productCode: String,
        customerID: String
    ): Flow<Resource<ResultTransaction>> = appRepository.getTopUpResult(productCode, customerID)
}