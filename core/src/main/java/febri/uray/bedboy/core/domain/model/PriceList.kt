package febri.uray.bedboy.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PriceList(
    @SerializedName("product_code") var productCode: String,
    @SerializedName("product_description") var productDescription: String? = null,
    @SerializedName("product_nominal") var productNominal: String? = null,
    @SerializedName("product_details") var productDetails: String? = null,
    @SerializedName("product_price") var productPrice: Int? = null,
    @SerializedName("product_type") var productType: String? = null,
    @SerializedName("active_period") var activePeriod: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("icon_url") var iconUrl: String? = null,
    @SerializedName("product_category") var productCategory: String? = null
) : Parcelable
