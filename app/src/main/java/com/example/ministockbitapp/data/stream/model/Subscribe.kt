package com.example.ministockbitapp.data.stream.model

import com.google.gson.annotations.SerializedName

data class Subscribe (
    @SerializedName("action")
    val action: String,
    @SerializedName("subs")
    val subs: List<String>
)

data class Ticker(
    @SerializedName("TYPE")
    val type: String,
    @SerializedName("MARKET")
    val market: String,
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    val toSymbol: String,
    @SerializedName("PRICE")
    val price: String,
    @SerializedName("LASTUPDATE")
    val time: Long
)