package com.example.ministockbitapp.data.crypto.remote

import com.example.ministockbitapp.data.crypto.model.CryptoResponseItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiClient {
    @GET("data/top/totaltoptiervolfull?limit=50&tsym=USD")
    fun getCrypto(
        @Query("page") page: Int): Single<CryptoResponseItem>
}