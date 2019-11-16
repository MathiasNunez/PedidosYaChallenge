package com.mnunez.pedidosya.activities

import android.os.Bundle
import android.view.View
import com.mnunez.core.extensions.hideKeyboard
import com.mnunez.pedidosya.R
import com.mnunez.pedidosya.base.PYBaseActivity
import com.mnunez.pedidosya.presenters.LoginPresenter
import com.mnunez.pedidosya.utils.SessionStore
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : PYBaseActivity<LoginActivity, LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener {
            if (isValidInput()) {
                hideKeyboard()
                mPresenter.login(user_input.text.toString(), password_input.text.toString())
            }
        }
    }

    fun onLoginSuccess(token: String) {
        SessionStore.token = token
        startActivity<RestaurantsActivity>()
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        finish()
    }

    private fun isValidInput(): Boolean {
        user_input.removeError()
        password_input.removeError()
        var isValid = true
        if (user_input.text.isNullOrEmpty()) {
            user_input.error = getString(R.string.login_error_username)
            isValid = false
            user_input.requestFocus()
        }
        if (password_input.text.isNullOrEmpty()) {
            password_input.error = getString(R.string.login_error_password)
            if (isValid) {
                password_input.requestFocus()
            }
            isValid = false
        }
        return isValid

    }

    fun getErrorMessage(): String = getString(R.string.login_error_invalid_credentials)

    override fun getFullscreenLoadingView(): View? {
        return progress_bar
    }

    override fun buildPresenter() {
        mPresenter = LoginPresenter()
    }
}