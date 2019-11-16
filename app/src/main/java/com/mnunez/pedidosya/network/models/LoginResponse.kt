package com.mnunez.pedidosya.network.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") var token: String
)