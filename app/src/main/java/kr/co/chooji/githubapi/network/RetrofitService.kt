package kr.co.chooji.githubapi.network

import kr.co.chooji.githubapi.app.AppInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://api.github.com/"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AppInterceptor())
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getGithubAPI(): RestAPI = retrofit.create(RestAPI::class.java)
}