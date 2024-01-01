package febri.uray.bedboy.core.data.source.local

import febri.uray.bedboy.core.data.source.local.entity.DefaultEntity
import febri.uray.bedboy.core.data.source.local.entity.PriceListEntity
import febri.uray.bedboy.core.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val appDao: AppDao) {

    fun getLog(): Flow<DefaultEntity> = appDao.getLog()

    fun insertLog(defaultEntity: DefaultEntity) = appDao.insertLog(defaultEntity)

    fun insertPriceList(priceListEntity: List<PriceListEntity>) =
        appDao.insertPriceList(priceListEntity)

    fun getAllPriceList(): Flow<List<PriceListEntity>> = appDao.getAllPriceList()

    fun getListMenu(): Flow<List<PriceListEntity>> = appDao.getListMenu()

    fun getProductList(productDesc: String, productCategory: String): Flow<List<PriceListEntity>> =
        appDao.getProductList(productDesc, productCategory)
}