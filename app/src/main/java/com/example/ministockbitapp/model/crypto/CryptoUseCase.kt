package com.example.ministockbitapp.model.crypto

import com.example.ministockbitapp.model.crypto.model.CryptoResponse
import io.reactivex.Single

interface CryptoUseCase {
    fun getCrypto(page: Int): Single<CryptoResponse>
}