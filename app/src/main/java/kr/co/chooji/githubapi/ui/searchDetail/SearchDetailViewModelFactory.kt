package kr.co.chooji.githubapi.ui.searchDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.chooji.githubapi.repository.SearchDetailRepository

class SearchDetailViewModelFactory(private val repository: SearchDetailRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchDetailViewModel(repository) as T
    }
}