package febri.uray.bedboy.core.domain.repository

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.core.domain.model.ProductList
import kotlinx.coroutines.flow.Flow


interface IAppRepository {

    fun getCheckBalance(requestData: JsonObject): Flow<Resource<Balance>>

    fun getPriceList(): Flow<Resource<List<ProductList>>>

    fun getMenuList(): Flow<List<MenuList>>

    fun getCallPackageList(provider: String): Flow<List<ProductList>>
}