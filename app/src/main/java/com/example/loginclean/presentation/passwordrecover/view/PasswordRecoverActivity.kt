package com.example.loginclean.presentation.passwordrecover.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.loginclean.LoginCleanApp
import com.example.loginclean.R
import com.example.loginclean.base.BaseActivity
import com.example.loginclean.presentation.main.view.HomeActivity
import com.example.loginclean.presentation.passwordrecover.PasswordRecoverContract
import com.example.loginclean.presentation.passwordrecover.presenter.PasswordRecoverPresenter
import kotlinx.android.synthetic.main.activity_password_recover.*
import javax.inject.Inject

class PasswordRecoverActivity : BaseActivity(), PasswordRecoverContract.PasswordRecoverView {

    @Inject
    lateinit var presenter: PasswordRecoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LoginCleanApp).getAppComponent()?.inject(this)
        presenter.attachView(this)
        password_recover_btn.setOnClickListener {
            recoverPassword()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_password_recover
    }

    override fun showError(msgError: String?) {
        toast(msgError)
    }

    override fun showProgress() {
        password_recover_progressBar.visibility = View.VISIBLE
        password_recover_btn.visibility = View.GONE
    }

    override fun hideProgress() {
        password_recover_progressBar.visibility = View.GONE
        password_recover_btn.visibility = View.VISIBLE
    }

    override fun recoverPassword() {
        val email:String = passwordrecover_email_txtv.text.trim().toString()
        if(!email.isEmpty()) presenter.sendPasswordRecover(email) else toast("E-mail is empty")    }

    override fun navigateToLogin() {
        toast("Sending password recover email")
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


}