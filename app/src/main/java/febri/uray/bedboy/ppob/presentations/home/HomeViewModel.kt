package febri.uray.bedboy.ppob.presentations.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import febri.uray.bedboy.core.util.MenuMapper.categoryMenuList
import febri.uray.bedboy.core.util.MenuMapper.generateMenuList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    fun balance(jsonObject: JsonObject) = useCase.getBalance(jsonObject).asLiveData()

    val services = listOf("MobilePulsa")

    val priceList = useCase.getPriceList().asLiveData()

    fun menuList(categoryMenu: String) =
        generateMenuList.filter { it.categoryMenu == categoryMenu }.ifEmpty { generateMenuList }

    val categoryMenus = categoryMenuList

    fun productCategoryList(selectedMenu: String) =
        useCase.getProductListCategory(selectedMenu).asLiveData()

}