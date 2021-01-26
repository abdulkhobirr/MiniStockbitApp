package com.example.ministockbitapp.data.crypto

import com.example.ministockbitapp.data.crypto.model.CryptoResponseItem
import com.example.ministockbitapp.data.crypto.remote.CryptoApi
import io.reactivex.Single

class CryptoDataStore (api: CryptoApi): CryptoRepository{
    val webService = api
    override fun getCrypto(page: Int): Single<CryptoResponseItem> {
        return webService.getCrypto(page)
            .map {
                it
            }
    }
}