package com.example.ministockbitapp.data.crypto.model

import com.google.gson.annotations.SerializedName

data class CryptoData(
    @SerializedName("CoinInfo")
    val coinInfo: CryptoCoinInfo,
    @SerializedName("RAW")
    val RAW: CryptoRawData?,
    @SerializedName("DISPLAY")
    val display: CryptoDisplayData?
)