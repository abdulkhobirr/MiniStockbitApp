package com.example.ministockbitapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ministockbitapp.model.crypto.CryptoUseCase
import com.example.ministockbitapp.model.crypto.model.CryptoResponse
import com.example.ministockbitapp.utils.viewmodel.Result
import com.example.ministockbitapp.utils.viewmodel.addTo
import com.example.ministockbitapp.utils.viewmodel.genericErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CryptoViewModel(
    private val useCase: CryptoUseCase,
    private val disposable: CompositeDisposable
): ViewModel() {
    val listCrypto = MutableLiveData<Result<CryptoResponse>>()

    init {
        listCrypto.value = Result.default()
    }

    fun getCrypto(page: Int){
        listCrypto.value = Result.loading()
        useCase.getCrypto(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listCrypto.value = Result.success(it)
            },{
                genericErrorHandler(it, listCrypto)
            })
            .addTo(disposable)
    }
}