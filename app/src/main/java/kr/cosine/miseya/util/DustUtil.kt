package kr.cosine.miseya.util

object DustUtil {

    fun getStatus(dustValue: String?): String {
        return when (dustValue?.toInt()) {
            null -> "불러오지 못함"
            in 0..30 -> "좋음"
            in 31..80 -> "보통"
            in 81..100 -> "나쁨"
            else -> "매우 나쁨"
        }
    }
}