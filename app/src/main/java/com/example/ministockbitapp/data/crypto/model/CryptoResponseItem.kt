package com.example.ministockbitapp.data.crypto.model

import com.google.gson.annotations.SerializedName

data class CryptoResponseItem(
    @SerializedName("Message")
    val message: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Data")
    val `data`: List<CryptoData>
)

