package febri.uray.bedboy.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseList(
    @SerializedName("data") var data: ResponseData? = ResponseData()
)
