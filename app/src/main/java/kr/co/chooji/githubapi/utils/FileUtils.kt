package kr.co.chooji.githubapi.utils

object FileUtils {

    fun jsonReader(fileName: String) = javaClass.classLoader.getResource(fileName).readText()
}