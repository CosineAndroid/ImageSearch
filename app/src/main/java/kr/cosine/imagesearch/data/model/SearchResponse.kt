package kr.cosine.imagesearch.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val meta: MetaResponse,
    val documents: List<DocumentResponse>,
)

data class DocumentResponse(
    val collection: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    val height: Int,
    val width: Int,
    @SerializedName("display_sitename") val displaySiteName: String,
    @SerializedName("doc_url") val docUrl: String,
    @SerializedName("datetime") val dateTime: String,
)

data class MetaResponse(
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)