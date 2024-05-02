package kr.cosine.miseya.domain.mapper

import kr.cosine.miseya.data.model.DustBodyResponse
import kr.cosine.miseya.data.model.DustHeaderResponse
import kr.cosine.miseya.data.model.DustItemResponse
import kr.cosine.miseya.data.model.DustInfoResponse
import kr.cosine.miseya.data.model.DustResponse
import kr.cosine.miseya.domain.model.DustBodyEntity
import kr.cosine.miseya.domain.model.DustEntity
import kr.cosine.miseya.domain.model.DustHeaderEntity
import kr.cosine.miseya.domain.model.DustInfoEntity
import kr.cosine.miseya.domain.model.DustItemEntity

fun DustResponse.toEntity(): DustEntity {
    return DustEntity(info.toEntity())
}

fun DustInfoResponse.toEntity(): DustInfoEntity {
    return DustInfoEntity(body.toEntity(), header.toEntity())
}

fun DustBodyResponse.toEntity(): DustBodyEntity {
    return DustBodyEntity(totalCount, items?.map(DustItemResponse::toEntity), pageNo, numOfRows)
}

fun DustHeaderResponse.toEntity(): DustHeaderEntity {
    return DustHeaderEntity(resultCode, resultMsg)
}

fun DustItemResponse.toEntity(): DustItemEntity {
    return DustItemEntity(
        so2Grade,
        coFlag,
        khaiValue,
        so2Value,
        coValue,
        pm25Flag,
        pm10Flag,
        o3Grade,
        pm10Value,
        khaiGrade,
        pm25Value,
        sidoName,
        no2Flag,
        no2Grade,
        o3Flag,
        pm25Grade,
        so2Flag,
        dataTime,
        coGrade,
        no2Value,
        stationName,
        pm10Grade,
        o3Value
    )
}

