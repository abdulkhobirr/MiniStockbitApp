package com.example.ministockbitapp.data.crypto.model

import com.google.gson.annotations.SerializedName

data class CryptoCoinInfo(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("FullName")
    val fullName: String
)