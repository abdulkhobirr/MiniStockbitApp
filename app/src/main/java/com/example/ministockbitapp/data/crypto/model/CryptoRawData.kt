package com.example.ministockbitapp.data.crypto.model

import com.google.gson.annotations.SerializedName

data class CryptoRawData(
    @SerializedName("USD")
    val USD: CurrencySymbol
)