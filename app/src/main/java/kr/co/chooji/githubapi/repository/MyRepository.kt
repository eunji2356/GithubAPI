package kr.co.chooji.githubapi.repository

import kr.co.chooji.githubapi.network.RestAPI
import kr.co.chooji.githubapi.network.RetrofitService

class MyRepository {

    fun getUser() = RetrofitService.getGithubAPI().getUser()

    fun mockUser(api: RestAPI) = api.getUser()
}