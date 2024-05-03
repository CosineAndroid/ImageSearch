package kr.cosine.imagesearch.view.model.event

import kr.cosine.imagesearch.view.model.state.item.SearchListItem

interface SearchListEvent {

    data class UpdateBookmark(
        val items: List<SearchListItem>
    ) : SearchListEvent
}