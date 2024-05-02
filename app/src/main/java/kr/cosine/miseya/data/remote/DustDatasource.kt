package kr.cosine.miseya.data.remote

import kr.cosine.miseya.data.model.DustResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface DustDatasource {

    @GET("getCtprvnRltmMesureDnsty")
    suspend fun getDust(
        @QueryMap requestMap: Map<String, String>
    ): DustResponse
}