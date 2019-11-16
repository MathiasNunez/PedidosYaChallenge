package com.mnunez.pedidosya.presenters

import com.mnunez.pedidosya.activities.LoginActivity
import com.mnunez.pedidosya.base.PYBasePresenter
import com.mnunez.pedidosya.network.ApiManager
import retrofit2.HttpException

class LoginPresenter : PYBasePresenter<LoginActivity>() {

    fun login(clientId: String, clientSecret: String) {
        view?.startLoading()
        val disposable = ApiManager.getInstance().login(clientId, clientSecret).subscribe({
            view?.stopLoading()
            view?.onLoginSuccess(it.token)
        }, {
            val error = it as? HttpException
            if (error != null) {
                when (error.code()) {
                    in 400..499 -> {
                        handleApiError(
                            showDefaultError = false,
                            it = Throwable(message = view?.getErrorMessage()),
                            retryAction = { login(clientId, clientSecret) })
                    }
                    else -> {
                        handleApiError(it, retryAction = { login(clientId, clientSecret) })
                    }
                }
            } else {
                handleApiError(it, retryAction = { login(clientId, clientSecret) })
            }
        })

        addDisposableToComposition(disposable)


    }
}