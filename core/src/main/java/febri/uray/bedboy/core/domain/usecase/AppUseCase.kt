package febri.uray.bedboy.core.domain.usecase

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.core.domain.model.ResultTransaction
import kotlinx.coroutines.flow.Flow


interface AppUseCase {
    fun getBalance(request: JsonObject): Flow<Resource<Balance>>
    fun getPriceList(): Flow<Resource<List<ProductList>>>
    fun getMenuList(): Flow<List<MenuList>>
    fun getCallPackageList(provider: String): Flow<List<ProductList>>
    fun getTopUpDataResult(productCode: String, customerID: String): Flow<Resource<ResultTransaction>>
}