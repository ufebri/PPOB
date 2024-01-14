package febri.uray.bedboy.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_list_postpaid_entity")
data class PostpaidProductEntity(

    @PrimaryKey @ColumnInfo(name = "code") var code: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo("status") var status: Int,
    @ColumnInfo("fee") var fee: Int,
    @ColumnInfo("komisi") var komisi: Int,
    @ColumnInfo("type") var type: String,
    @ColumnInfo("category") var category: String
)