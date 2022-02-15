package kr.co.chooji.githubapi

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kr.co.chooji.githubapi.network.RestAPI
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.Q])
internal class HomeViewModelTest {

    private lateinit var server: MockWebServer
    private lateinit var mockUrl: HttpUrl

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val api by lazy {
        retrofit.create(RestAPI::class.java)
    }

    @Before
    fun setup(){
        System.setProperty("javax.net.ssl.trustStore", "NONE")
        server = MockWebServer()
        server.start()
        mockUrl = server.url("/")
    }

    @Test
    fun `getSearchUser API`(){
        val mockResponse = MockResponse().apply {
            setResponseCode(HttpURLConnection.HTTP_OK)
            //TODO json 파일
            setBody("{}")
        }
        server.enqueue(mockResponse)
        println(">>>>> body : $mockResponse")

        api.getSearchUser("eunji", 1, 10)
            .test()
            .assertValue { value ->
                println("value : $value")
                true
            }
            .assertComplete()
            .assertNoErrors()
    }

    @After
    fun tearDown(){
        server.shutdown()
    }
}