package com.example.ministockbitapp.data.crypto.model

import com.google.gson.annotations.SerializedName

data class CurrencySymbol(
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    val toSymbol: String,
    @SerializedName("MARKET")
    val market: String,
    @SerializedName("PRICE")
    val price: String,
    @SerializedName("LASTUPDATE")
    val lastUpdate: String,
    @SerializedName("CHANGEDAY")
    val changeDay: String,
    @SerializedName("CHANGEPCTDAY")
    val changePct: String
)