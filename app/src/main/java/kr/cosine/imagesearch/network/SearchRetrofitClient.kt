package kr.cosine.imagesearch.network

import kr.cosine.imagesearch.data.remote.SearchDatasource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SearchRetrofitClient {

    private const val KAKAO_API_BASE_URL = "https://dapi.kakao.com"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(KAKAO_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val searchDatasource: SearchDatasource by lazy {
        retrofit.create(SearchDatasource::class.java)
    }
}