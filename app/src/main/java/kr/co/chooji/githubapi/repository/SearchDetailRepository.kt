package kr.co.chooji.githubapi.repository

import kr.co.chooji.githubapi.network.RestAPI
import kr.co.chooji.githubapi.network.RetrofitService

class SearchDetailRepository {

    fun getUserOne(userName: String) = RetrofitService.getGithubAPI().getUserOne(userName)

    fun mockUserOne(api: RestAPI, userName: String) = api.getUserOne(userName)
}