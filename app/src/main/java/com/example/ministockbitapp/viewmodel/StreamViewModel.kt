package com.example.ministockbitapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ministockbitapp.data.stream.StreamRepository
import com.example.ministockbitapp.data.stream.model.TransactionBook
import com.example.ministockbitapp.utils.viewmodel.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StreamViewModel(
    private val repository: StreamRepository,
    private val disposable: CompositeDisposable): ViewModel() {
    val liveCrypto = MutableLiveData<TransactionBook>()

    fun streamCrypto(){
        repository.observeTransactionBook().toObservable()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                liveCrypto.postValue(it)
            }.addTo(disposable)
    }

    override fun onCleared() {
        if (!disposable.isDisposed) disposable.dispose()
        super.onCleared()
    }
}