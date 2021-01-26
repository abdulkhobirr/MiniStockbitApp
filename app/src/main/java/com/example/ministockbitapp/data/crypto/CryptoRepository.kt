package com.example.ministockbitapp.data.crypto

import com.example.ministockbitapp.data.crypto.model.CryptoResponseItem
import io.reactivex.Single

interface CryptoRepository {
    fun getCrypto(page: Int): Single<CryptoResponseItem>
}