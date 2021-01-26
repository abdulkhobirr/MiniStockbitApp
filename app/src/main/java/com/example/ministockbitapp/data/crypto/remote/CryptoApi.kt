package com.example.ministockbitapp.data.crypto.remote

import com.example.ministockbitapp.data.crypto.model.CryptoResponseItem
import io.reactivex.Single

class CryptoApi (private val apiClient: CryptoApiClient): CryptoApiClient{

    override fun getCrypto(page: Int): Single<CryptoResponseItem> {
        return apiClient.getCrypto(page)
    }
}