package kr.co.chooji.githubapi.model.my

import com.google.gson.annotations.SerializedName

data class Plan(
    @SerializedName("name") var name: String = "",
    @SerializedName("space") var space:Int  = 0,
    @SerializedName("private_repos") var privateRepos: Int = 0,
    @SerializedName("collaborators") var collaborators:Int = 0
)