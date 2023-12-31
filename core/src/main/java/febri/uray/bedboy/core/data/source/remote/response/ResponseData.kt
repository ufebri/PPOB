package febri.uray.bedboy.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import febri.uray.bedboy.core.domain.model.PriceList

data class ResponseData(

    //This is for price list
    @SerializedName("pricelist") var pricelist: List<PriceList> = arrayListOf(),

    //This is for balance
    @SerializedName("balance") var balance: Int? = null,

    //This is general message
    @SerializedName("message") var message: String? = null,
    @SerializedName("rc") var rc: String? = null,
    @SerializedName("status") var status: String? = null
)