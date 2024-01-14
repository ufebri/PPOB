package febri.uray.bedboy.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import febri.uray.bedboy.core.domain.model.ProductList

data class ResponseData(

    /* Prepaid */
    //This is for price list
    @SerializedName("pricelist") var pricelist: List<ProductList> = arrayListOf(),

    //This is for balance
    @SerializedName("balance") var balance: Int? = null,

    //This is general message
    @SerializedName("message") var message: String? = null,
    @SerializedName("rc") var rc: String? = null,
    @SerializedName("status") var status: String? = null,

    //This is for topup
    @SerializedName("ref_id") var refId: String? = null,
    @SerializedName("product_code") var productCode: String? = null,
    @SerializedName("customer_id") var customerId: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("tr_id") var trId: Int? = null,
    @SerializedName("sn") var sn: String? = null,

    /* Postpaid */
    @SerializedName("pasca") var pascaList: List<ResponsePostPaid> = arrayListOf()
)