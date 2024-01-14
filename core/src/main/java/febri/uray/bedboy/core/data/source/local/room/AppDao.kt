package febri.uray.bedboy.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import febri.uray.bedboy.core.data.source.local.entity.PostpaidProductEntity
import febri.uray.bedboy.core.data.source.local.entity.PriceListEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceListPrepaid(entity: List<PriceListEntity>)

    @Query("SELECT * FROM price_list_entity")
    fun getAllPriceList(): Flow<List<PriceListEntity>>

    @Query("SELECT * FROM price_list_entity WHERE product_category = :productCategory GROUP BY product_description")
    fun getProductListCategory(productCategory: String): Flow<List<PriceListEntity>>

    @Query("SELECT * FROM price_list_entity WHERE product_description = :productDesc AND product_category = :productCategory")
    fun getProductList(productDesc: String, productCategory: String): Flow<List<PriceListEntity>>

    @Query("SELECT * FROM price_list_postpaid_entity")
    fun getAllPriceListPostpaid(): Flow<List<PostpaidProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceListPostpaid(entity: List<PostpaidProductEntity>)
}