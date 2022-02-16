package kr.co.chooji.githubapi.repository

import kr.co.chooji.githubapi.network.RestAPI
import kr.co.chooji.githubapi.network.RetrofitService


class HomeRepository {

    companion object{
        const val PER_PAGE = 10
    }

    fun getSearchUser(search:String, page: Int) = RetrofitService.getGithubAPI().getSearchUser(search, page, PER_PAGE)

    fun mockSearchUser(api: RestAPI, search: String) = api.getSearchUser(search, 1, PER_PAGE)
}