package kr.cosine.miseya.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kr.cosine.miseya.data.repository.DustRepository
import kr.cosine.miseya.data.usecase.DustUseCase
import kr.cosine.miseya.domain.repository.DustRepositoryImpl
import kr.cosine.miseya.network.DustRetrofitClient
import kr.cosine.miseya.view.model.DustViewModel

class DustViewModelFactory : ViewModelProvider.Factory {

    private val dustRepository: DustRepository = DustRepositoryImpl(DustRetrofitClient.dustDatasource)
    private val dustUseCase = DustUseCase(dustRepository)

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return DustViewModel(dustUseCase) as T
    }
}