package com.mnunez.pedidosya.network.interfaces

import com.mnunez.pedidosya.network.models.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApi {

    @GET("search/restaurants")
    fun searchRestaurants(
        @Header("Authorization") token: String,
        @Query("point", encoded = true) location: String,
        @Query("country", encoded = true) country: Int = 1,
        @Query("max", encoded = true) max: Int = 20,
        @Query("offset", encoded = true) offset: Int
    ): Observable<SearchResponse>
}