package kr.cosine.miseya.data.model

import com.google.gson.annotations.SerializedName

data class DustResponse(
    @SerializedName("response")
    val info: DustInfoResponse
)

data class DustInfoResponse(
    val body: DustBodyResponse,
    val header: DustHeaderResponse
)

data class DustBodyResponse(
    val totalCount: Int,
    val items: List<DustItemResponse>?,
    val pageNo: Int,
    val numOfRows: Int
)

data class DustHeaderResponse(
    val resultCode: String,
    val resultMsg: String
)

data class DustItemResponse(
    val so2Grade: String?,
    val coFlag: String?,
    val khaiValue: String?,
    val so2Value: String?,
    val coValue: String?,
    val pm25Flag: String?,
    val pm10Flag: String?,
    val o3Grade: String?,
    val pm10Value: String?,
    val khaiGrade: String?,
    val pm25Value: String?,
    val sidoName: String,
    val no2Flag: String?,
    val no2Grade: String?,
    val o3Flag: String?,
    val pm25Grade: String?,
    val so2Flag: String?,
    val dataTime: String?,
    val coGrade: String?,
    val no2Value: String?,
    val stationName: String,
    val pm10Grade: String?,
    val o3Value: String?
)