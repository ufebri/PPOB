package febri.uray.bedboy.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import febri.uray.bedboy.core.data.source.local.entity.PostpaidProductEntity
import febri.uray.bedboy.core.data.source.local.entity.PriceListEntity

@Database(
    entities = [PriceListEntity::class, PostpaidProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}