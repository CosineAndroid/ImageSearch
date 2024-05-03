package kr.cosine.imagesearch.view.model.state

import kr.cosine.imagesearch.view.model.state.item.SearchListItem

data class SearchListUiState(
    val items: List<SearchListItem> = emptyList()
)