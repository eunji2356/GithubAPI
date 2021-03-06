package kr.co.chooji.githubapi.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kr.co.chooji.githubapi.model.my.User
import kr.co.chooji.githubapi.network.RetrofitService
import kr.co.chooji.githubapi.repository.MyRepository

class MyViewModel(private val repository: MyRepository): ViewModel() {

    private var disposable: CompositeDisposable = CompositeDisposable()

    var userInfo = MutableLiveData<User>()

    fun getUser(){
        disposable.add(repository.getUser()
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