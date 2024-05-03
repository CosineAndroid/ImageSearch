package kr.cosine.imagesearch.domain.mapper

import kr.cosine.imagesearch.data.model.DocumentResponse
import kr.cosine.imagesearch.data.model.MetaResponse
import kr.cosine.imagesearch.data.model.SearchResponse
import kr.cosine.imagesearch.domain.model.DocumentEntity
import kr.cosine.imagesearch.domain.model.MetaEntity
import kr.cosine.imagesearch.domain.model.SearchEntity

fun SearchResponse.toEntity(): SearchEntity {
    return SearchEntity(meta.toEntity(), documents.map(DocumentResponse::toEntity))
}

fun MetaResponse.toEntity(): MetaEntity {
    return MetaEntity(totalCount, pageableCount, isEnd)
}

fun DocumentResponse.toEntity(): DocumentEntity {
    return DocumentEntity(
        collection,
        thumbnailUrl,
        imageUrl,
        width,
        height,
        displaySiteName,
        docUrl,
        dateTime,
    )
}