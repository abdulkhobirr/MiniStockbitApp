package com.example.ministockbitapp.data.stream

import com.example.ministockbitapp.data.stream.model.TransactionBook
import io.reactivex.Flowable

interface StreamRepository {
    fun observeTransactionBook(): Flowable<TransactionBook>
}