package com.example.ministockbitapp.model.crypto

import com.example.ministockbitapp.data.crypto.CryptoRepository
import com.example.ministockbitapp.model.crypto.model.CryptoResponse
import io.reactivex.Single

//class CryptoInteractor(private val repository: CryptoRepository): CryptoUseCase {
//    override fun getCrypto(page: Int): Single<CryptoResponse> {
//        return repository.getCrypto(page)
//            .map {
//                it.toCryptoResponse()
//            }
//    }
//}