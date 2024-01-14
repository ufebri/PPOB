package febri.uray.bedboy.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ResponsePostPaid(
    @SerializedName("code") var code: String,
    @SerializedName("name") var name: String,
    @SerializedName("status") var status: Int,
    @SerializedName("fee") var fee: Int,
    @SerializedName("komisi") var komisi: Int,
    @SerializedName("type") var type: String,
    @SerializedName("category") var category: String
)
