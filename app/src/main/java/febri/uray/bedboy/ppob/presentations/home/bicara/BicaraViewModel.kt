package febri.uray.bedboy.ppob.presentations.home.bicara

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class BicaraViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    val providerList = listOf("XL", "Telkomsel", "Three")

    fun packageList(provider: String) = useCase.getCallPackageList(provider).asLiveData()
}