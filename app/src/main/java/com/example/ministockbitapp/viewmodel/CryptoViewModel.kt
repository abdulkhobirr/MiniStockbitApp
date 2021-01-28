package com.example.ministockbitapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ministockbitapp.data.crypto.CryptoRepository
import com.example.ministockbitapp.data.crypto.model.CryptoResponseItem
import com.example.ministockbitapp.utils.viewmodel.Result
import com.example.ministockbitapp.utils.viewmodel.addTo
import com.example.ministockbitapp.utils.viewmodel.genericErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CryptoViewModel(
    private val repository: CryptoRepository,
    private val disposable: CompositeDisposable
): ViewModel() {
    val listCrypto = MutableLiveData<Result<CryptoResponseItem>>()
    var pageCount: Int = 0

    init {
        listCrypto.value = Result.default()
    }

    fun getCrypto(){
        listCrypto.value = Result.loading()
        repository.getCrypto(pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listCrypto.value = Result.success(it)
            },{
                genericErrorHandler(it, listCrypto)
            })
            .addTo(disposable)
    }

    fun incrementPageCount(){
        pageCount++
    }

    fun resetPageCount(){
        pageCount = 0
    }

    override fun onCleared() {
        if (!disposable.isDisposed) disposable.dispose()
        super.onCleared()
    }
}