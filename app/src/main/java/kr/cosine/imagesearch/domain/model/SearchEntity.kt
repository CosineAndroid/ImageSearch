package kr.cosine.imagesearch.domain.model

data class SearchEntity(
    val meta: MetaEntity,
    val documents: List<DocumentEntity>,
)

data class DocumentEntity(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val height: Int,
    val width: Int,
    val displaySiteName: String,
    val docUrl: String,
    val dateTime: String,
)

data class MetaEntity(
    val pageableCount: Int,
    val totalCount: Int,
    val isEnd: Boolean
)