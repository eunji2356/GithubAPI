package kr.co.chooji.githubapi.app

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class AppInterceptor: Interceptor {

    // TODO access_token 필수
    private val accessToken: String = ""

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain){
        val request = request().newBuilder()
            .addHeader("accept", "application/vnd.github.v3+json")
            .addHeader("Authorization", "token $accessToken")
            .build()
        proceed(request)
    }
}