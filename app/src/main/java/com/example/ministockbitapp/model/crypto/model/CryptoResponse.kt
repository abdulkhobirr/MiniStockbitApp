package com.example.ministockbitapp.model.crypto.model

import com.example.ministockbitapp.data.crypto.model.Data

data class CryptoResponse(
    val message: String,
    val type: String,
    val data: List<Data>
)

data class Data(
    val coinInfo: CoinInfo,
    val RAW: RAW,
    val display: Display
)

data class CoinInfo(
    val id: String,
    val name: String,
    val fullName: String
)

data class RAW(
    val USD: USD
)

data class Display(
    val USD: USD
)

data class USD(
    val fromSymbol: String,
    val toSymbol: String,
    val market: String,
    val price: String,
    val lastUpdate: String,
    val changeDay: String,
    val changePct: String
)