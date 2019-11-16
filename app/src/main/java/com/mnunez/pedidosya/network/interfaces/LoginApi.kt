package com.mnunez.pedidosya.network.interfaces

import com.mnunez.pedidosya.network.models.LoginResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface LoginApi {

    @GET("tokens")
    fun login(
        @Query("clientId", encoded = true) clientId: String,
        @Query("clientSecret", encoded = true) clientSecret: String
    ): Observable<LoginResponse>
}