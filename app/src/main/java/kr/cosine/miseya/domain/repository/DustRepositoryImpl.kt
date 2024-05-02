package kr.cosine.miseya.domain.repository

import kr.cosine.miseya.BuildConfig
import kr.cosine.miseya.data.model.DustResponse
import kr.cosine.miseya.data.remote.DustDatasource
import kr.cosine.miseya.data.repository.DustRepository

class DustRepositoryImpl(
    private val dustDatasource: DustDatasource
) : DustRepository {

    override suspend fun getDust(requestMap: Map<String, String>): DustResponse {
        return dustDatasource.getDust(requestMap)
    }

    override suspend fun getDust(sidoName: String): DustResponse {
        return getDust(defaultRequestMap + ("sidoName" to sidoName))
    }

    private companion object {
        val defaultRequestMap = mapOf(
            "serviceKey" to BuildConfig.DUST_API_KEY,
            "returnType" to "json",
            "numOfRows" to "100",
            "pageNo" to "1",
            "ver" to "1.0"
        )
    }
}