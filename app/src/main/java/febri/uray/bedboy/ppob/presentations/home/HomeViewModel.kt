package febri.uray.bedboy.ppob.presentations.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    fun balance(jsonObject: JsonObject) = useCase.getBalance(jsonObject).asLiveData()

    val services = listOf("MobilePulsa")

    val priceList = useCase.getPriceList().asLiveData()

    val menuList = useCase.getMenuList().asLiveData()
}