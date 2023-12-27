package febri.uray.bedboy.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("balance") var balance: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("rc") var rc: String? = null,
    @SerializedName("status") var status: String? = null
)