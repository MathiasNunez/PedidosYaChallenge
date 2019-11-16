package com.mnunez.pedidosya.network

import com.mnunez.core.network.BaseRestApi
import com.mnunez.pedidosya.BuildConfig
import com.mnunez.pedidosya.network.interfaces.LoginApi
import com.mnunez.pedidosya.network.interfaces.SearchApi
import com.mnunez.pedidosya.network.models.LoginResponse
import com.mnunez.pedidosya.network.models.SearchResponse
import com.mnunez.pedidosya.utils.SessionStore
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiManager : BaseRestApi() {

    companion object {
        @Volatile
        private var instance: ApiManager? = null

        fun getInstance(): ApiManager =
            instance ?: synchronized(this) {
                instance ?: ApiManager()
            }
    }

    fun login(clientId: String, clientSecret: String): Observable<LoginResponse> {
        return getApi(BuildConfig.URL_BASE, LoginApi::class.java)
            .login(clientId = clientId, clientSecret = clientSecret)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchRestaurants(location: String, offset: Int): Observable<SearchResponse> {
        return getApi(BuildConfig.URL_BASE, SearchApi::class.java)
            .searchRestaurants(
                token = SessionStore.token ?: "",
                location = location,
                offset = offset
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}