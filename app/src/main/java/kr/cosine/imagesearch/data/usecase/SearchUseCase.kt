package kr.cosine.imagesearch.data.usecase

import kr.cosine.imagesearch.data.repository.SearchRepository
import kr.cosine.imagesearch.domain.mapper.toEntity
import kr.cosine.imagesearch.domain.model.SearchEntity

class SearchUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String): SearchEntity {
        return searchRepository.getSearch(query).toEntity()
    }
}