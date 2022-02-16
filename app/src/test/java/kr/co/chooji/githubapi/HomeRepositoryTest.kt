package kr.co.chooji.githubapi

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kr.co.chooji.githubapi.network.RestAPI
import kr.co.chooji.githubapi.repository.HomeRepository
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
internal class HomeRepositoryTest {

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

    private val repository = HomeRepository()

    @Before
    fun setup(){
        System.setProperty("javax.net.ssl.trustStore", "NONE")
        server = MockWebServer()
        server.start()
        mockUrl = server.url("/")
    }

    @Test
    fun `getSearchUser API`(){
        val jsonFile = javaClass.classLoader.getResource("search_user.json").readText()
        val mockResponse = MockResponse().apply {
            setResponseCode(HttpURLConnection.HTTP_OK)
            setBody(jsonFile)
        }
        server.enqueue(mockResponse)

        repository.mockSearchUser(api, "eunji")
            .test()
            .assertValue { it.items.size == 10 }
            .assertValue { it.items[0].login.contains("eunji", true) }
            .assertComplete()
            .assertNoErrors()
    }

    @After
    fun tearDown(){
        server.shutdown()
    }
}