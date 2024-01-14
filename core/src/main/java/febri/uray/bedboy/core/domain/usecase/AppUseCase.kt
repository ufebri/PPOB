package febri.uray.bedboy.core.domain.usecase

import com.google.gson.JsonObject
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.Balance
import febri.uray.bedboy.core.domain.model.PostpaidProduct
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.core.domain.model.ResultTransaction
import kotlinx.coroutines.flow.Flow


interface AppUseCase {
    fun getBalance(request: JsonObject): Flow<Resource<Balance>>
    fun getPriceList(): Flow<Resource<List<ProductList>>>
    fun getProductListCategory(productCategory: String): Flow<List<String>>
    fun getProductListDesc(productDesc: String, productCategory: String): Flow<List<ProductList>>
    fun getTopUpDataResult(
        productCode: String, customerID: String
    ): Flow<Resource<ResultTransaction>>

    fun getProductListPostpaid(): Flow<Resource<List<PostpaidProduct>>>
}