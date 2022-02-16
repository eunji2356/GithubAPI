package kr.co.chooji.githubapi.ui.searchDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kr.co.chooji.githubapi.model.my.User
import kr.co.chooji.githubapi.network.RetrofitService
import kr.co.chooji.githubapi.repository.SearchDetailRepository

class SearchDetailViewModel(private val repository: SearchDetailRepository): ViewModel() {

    private var disposable = CompositeDisposable()

    var userInfo = MutableLiveData<User>()

    fun getUserOne(userName: String){
        disposable.add(repository.getUserOne(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                userInfo.value = res
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