package febri.uray.bedboy.ppob

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

//    var jsonObject: JsonObject = JsonObject().apply {
//        addProperty("username", "")
//        addProperty("sign", "")
//    }

    fun balance(jsonObject: JsonObject) = useCase.getBalance(jsonObject).asLiveData()

    val services = listOf("MobilePulsa")
}