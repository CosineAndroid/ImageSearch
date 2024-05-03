package kr.cosine.imagesearch.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.cosine.imagesearch.data.usecase.SearchUseCase
import kr.cosine.imagesearch.domain.model.SearchEntity
import kr.cosine.imagesearch.view.model.event.SearchListEvent
import kr.cosine.imagesearch.view.model.state.SearchListUiState
import kr.cosine.imagesearch.view.model.state.item.SearchListItem

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SearchListUiState> = MutableStateFlow(SearchListUiState())
    val uiState: StateFlow<SearchListUiState> get() = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<SearchListEvent> = MutableSharedFlow()
    val event: SharedFlow<SearchListEvent> get() = _event

    fun onSearch(query: String) = viewModelScope.launch {
        val searchEntity = searchUseCase(query)
        val items = createSearchListItems(searchEntity)
        _uiState.update { prevUiState ->
            prevUiState.copy(
                items = items
            )
        }
    }

    private fun createSearchListItems(entity: SearchEntity): List<SearchListItem> {
        return entity.documents.map {
            SearchListItem.ImageItem(
                it.docUrl,
                it.thumbnailUrl,
                it.displaySiteName,
                it.dateTime
            )
        }
    }

    fun onBookmark(item: SearchListItem) = viewModelScope.launch {
        val items = _uiState.value.items.toMutableList()
        val index = items.indexOfFirst {
            it.documentUrl == item.documentUrl
        }
        val newItem = when (item) {
            is SearchListItem.ImageItem -> item.copy(
                isBookmarked = !item.isBookmarked
            )
        }
        items[index] = newItem
        _uiState.update { prevUiState ->
            prevUiState.copy(
                items = items
            )
        }
        val bookmarkedItems = uiState.value.items.filter(SearchListItem::isBookmarked)
        val updateBookmark = SearchListEvent.UpdateBookmark(bookmarkedItems)
        _event.emit(updateBookmark)
    }
}