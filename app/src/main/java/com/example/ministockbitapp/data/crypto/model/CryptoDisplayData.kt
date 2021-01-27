package com.example.ministockbitapp.data.crypto.model

import com.google.gson.annotations.SerializedName

data class CryptoDisplayData(
    @SerializedName("USD")
    val USD: CurrencySymbol?
)