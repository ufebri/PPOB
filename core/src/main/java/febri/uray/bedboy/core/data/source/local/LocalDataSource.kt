package febri.uray.bedboy.core.data.source.local

import febri.uray.bedboy.core.data.source.local.entity.PriceListEntity
import febri.uray.bedboy.core.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val appDao: AppDao) {

    fun insertPriceList(priceListEntity: List<PriceListEntity>) =
        appDao.insertPriceList(priceListEntity)

    fun getAllPriceList(): Flow<List<PriceListEntity>> = appDao.getAllPriceList()

    fun getProductListCategory(productCategory: String): Flow<List<PriceListEntity>> =
        appDao.getProductListCategory(productCategory)

    fun getProductList(productDesc: String, productCategory: String): Flow<List<PriceListEntity>> =
        appDao.getProductList(productDesc, productCategory)
}