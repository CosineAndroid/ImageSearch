package kr.cosine.imagesearch.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kr.cosine.imagesearch.data.usecase.SearchUseCase
import kr.cosine.imagesearch.domain.repository.SearchRepositoryImpl
import kr.cosine.imagesearch.network.SearchRetrofitClient
import kr.cosine.imagesearch.view.model.SearchViewModel

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val searchUseCase by lazy {
        val searchRepository = SearchRepositoryImpl(SearchRetrofitClient.searchDatasource)
        SearchUseCase(searchRepository)
    }

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return SearchViewModel(searchUseCase) as T
    }
}