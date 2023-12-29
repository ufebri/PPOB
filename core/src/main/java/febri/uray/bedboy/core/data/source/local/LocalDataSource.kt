package febri.uray.bedboy.core.data.source.local

import febri.uray.bedboy.core.data.source.local.entity.DefaultEntity
import febri.uray.bedboy.core.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val appDao: AppDao) {

    fun getLog(): Flow<DefaultEntity> = appDao.getLog()

    fun insertLog(defaultEntity: DefaultEntity) = appDao.insertLog(defaultEntity)
}