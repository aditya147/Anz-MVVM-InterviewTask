package me.adityarajawat.myanzinterviewtask.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("company")
    val company: String = "",
    @SerializedName("username")
    val userName: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("address")
    val address: String = "",
    @SerializedName("zip")
    val zip: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("photo")
    val photo: String = ""
)