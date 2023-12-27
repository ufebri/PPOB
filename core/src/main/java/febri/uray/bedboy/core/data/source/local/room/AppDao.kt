package febri.uray.bedboy.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import febri.uray.bedboy.core.data.source.local.entity.DefaultEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {

    @Query("SELECT * FROM log_entity")
    fun getLog(): Flow<DefaultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLog(entity: DefaultEntity)
}