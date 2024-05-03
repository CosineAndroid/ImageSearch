package kr.cosine.imagesearch.data.repository

import kr.cosine.imagesearch.data.model.SearchResponse

interface SearchRepository {

    suspend fun getSearch(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchResponse
}