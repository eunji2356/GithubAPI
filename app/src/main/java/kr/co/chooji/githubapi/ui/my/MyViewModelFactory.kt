package kr.co.chooji.githubapi.ui.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.chooji.githubapi.repository.MyRepository

class MyViewModelFactory(private val repository: MyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(repository) as T
    }
}