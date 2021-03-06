package kr.co.chooji.githubapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kr.co.chooji.githubapi.model.search.SearchUser
import kr.co.chooji.githubapi.network.RetrofitService
import kr.co.chooji.githubapi.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    val isNotEmpty =  MutableLiveData<Boolean>().apply {
        value = false
    }
    val userList = MutableLiveData<MutableList<SearchUser>>()

    fun getSearchUser(search:String, page: Int){
        disposable.add(repository.getSearchUser(search, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if(page == 1 && res.items.size > 0){
                    isNotEmpty.value = true
                }
                userList.value = res.items
            }, { e ->
                e.printStackTrace()
            })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}