package febri.uray.bedboy.prepaid.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class PrepaidViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    fun packageList(selectedProduct: String, category: String) = useCase.getProductListDesc(selectedProduct, category).asLiveData()
}