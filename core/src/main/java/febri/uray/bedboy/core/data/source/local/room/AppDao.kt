package febri.uray.bedboy.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import febri.uray.bedboy.core.data.source.local.entity.DefaultEntity
import febri.uray.bedboy.core.data.source.local.entity.PriceListEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    @Query("SELECT * FROM log_entity")
    fun getLog(): Flow<DefaultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLog(entity: DefaultEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(entity: List<PriceListEntity>)

    @Query("SELECT * FROM price_list_entity")
    fun getAllPriceList(): Flow<List<PriceListEntity>>

    @Query("SELECT * FROM price_list_entity GROUP BY product_category")
    fun getListMenu(): Flow<List<PriceListEntity>>

    @Query("SELECT * FROM price_list_entity WHERE product_description = :productDesc AND product_category = :productCategory")
    fun getProductList(productDesc: String, productCategory: String): Flow<List<PriceListEntity>>
}