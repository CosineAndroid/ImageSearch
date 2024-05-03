package kr.cosine.imagesearch.data.remote

import kr.cosine.imagesearch.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchDatasource {

    @GET("/v2/search/image")
    suspend fun getSearch(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): SearchResponse
}