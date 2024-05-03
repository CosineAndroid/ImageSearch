package kr.cosine.imagesearch.domain.repository

import kr.cosine.imagesearch.data.model.SearchResponse
import kr.cosine.imagesearch.data.remote.SearchDatasource
import kr.cosine.imagesearch.data.repository.SearchRepository

class SearchRepositoryImpl(
    private val searchDatasource: SearchDatasource
) : SearchRepository {

    override suspend fun getSearch(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchResponse {
        return searchDatasource.getSearch(query, sort, page, size)
    }
}