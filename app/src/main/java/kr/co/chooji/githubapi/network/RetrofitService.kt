package kr.co.chooji.githubapi.network

import kr.co.chooji.githubapi.app.AppInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://api.github.com/"

    var api: RestAPI

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AppInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RestAPI::class.java)
    }
}