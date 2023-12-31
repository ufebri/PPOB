package febri.uray.bedboy.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_list_entity")
data class PriceListEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "product_code")
    var productCode: String,

    @ColumnInfo(name = "product_description") var productDescription: String? = null,
    @ColumnInfo(name = "product_nominal") var productNominal: String? = null,
    @ColumnInfo(name = "product_details") var productDetails: String? = null,
    @ColumnInfo(name = "product_price") var productPrice: Int? = null,
    @ColumnInfo(name = "product_type") var productType: String? = null,
    @ColumnInfo(name = "active_period") var activePeriod: String? = null,
    @ColumnInfo(name = "status") var status: String? = null,
    @ColumnInfo(name = "icon_url") var iconUrl: String? = null,
    @ColumnInfo(name = "product_category") var productCategory: String? = ""
)