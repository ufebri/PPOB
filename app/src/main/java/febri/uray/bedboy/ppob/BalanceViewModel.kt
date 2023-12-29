package febri.uray.bedboy.ppob

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(useCase: AppUseCase): ViewModel() {

    private var jsonObject: JsonObject = JsonObject().apply {
        addProperty("username", "")
        addProperty("sign", "")
    }

    val balance = useCase.getBalance(jsonObject).asLiveData()
}