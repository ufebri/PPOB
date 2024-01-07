package febri.uray.bedboy.prepaid.topup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class TopUpViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    fun doTopUp(productCode: String, customerID: String) =
        useCase.getTopUpDataResult(productCode, customerID).asLiveData()
}