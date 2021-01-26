package com.example.ministockbitapp.data.crypto.model

import com.example.ministockbitapp.model.crypto.model.CryptoResponse
import com.google.gson.annotations.SerializedName

data class CryptoResponseItem(
    @SerializedName("Message")
    val message: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Data")
    val `data`: List<Data>?
){
    fun toCryptoResponse(): CryptoResponse{
        return CryptoResponse(
            message.orEmpty(),
            type.orEmpty(),
            `data`.orEmpty()
        )
    }
}

data class Data(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo?,
    @SerializedName("RAW")
    val RAW: RAW?,
    @SerializedName("DISPLAY")
    val display: Display?
)

data class CoinInfo(
    @SerializedName("Id")
    val id: String?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("FullName")
    val fullName: String?
)

data class RAW(
    @SerializedName("USD")
    val USD: USD?
)

data class Display(
    @SerializedName("USD")
    val USD: USD?
)

data class USD(
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String?,
    @SerializedName("TOSYMBOL")
    val toSymbol: String?,
    @SerializedName("MARKET")
    val market: String?,
    @SerializedName("PRICE")
    val price: String?,
    @SerializedName("LASTUPDATE")
    val lastUpdate: String?,
    @SerializedName("CHANGEDAY")
    val changeDay: String?,
    @SerializedName("CHANGEPCTDAY")
    val changePct: String?
)

