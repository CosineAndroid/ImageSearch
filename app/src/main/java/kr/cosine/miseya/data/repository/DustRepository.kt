package kr.cosine.miseya.data.repository

import kr.cosine.miseya.data.model.DustResponse

interface DustRepository {

    suspend fun getDust(requestMap: Map<String, String>): DustResponse

    suspend fun getDust(sidoName: String): DustResponse
}