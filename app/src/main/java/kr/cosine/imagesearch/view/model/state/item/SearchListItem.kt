package kr.cosine.imagesearch.view.model.state.item

sealed interface SearchListItem {

    val documentUrl: String

    val thumbnailUrl: String

    val siteName: String

    val dateTime: String

    val isBookmarked: Boolean

    data class ImageItem(
        override val documentUrl: String,
        override val thumbnailUrl: String,
        override val siteName: String,
        override val dateTime: String,
        override val isBookmarked: Boolean = false
    ) : SearchListItem
}