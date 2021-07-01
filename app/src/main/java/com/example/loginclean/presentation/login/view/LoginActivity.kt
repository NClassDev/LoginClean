package com.example.loginclean.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.loginclean.R
import com.example.loginclean.base.BaseActivity
import com.example.loginclean.domain.interactors.logininteractor.SignInInteractorImpl
import com.example.loginclean.presentation.login.LoginContract
import com.example.loginclean.presentation.login.presenter.LoginPresenter
import com.example.loginclean.presentation.main.view.HomeActivity
import com.example.loginclean.presentation.passwordrecover.view.PasswordRecoverActivity
import com.example.loginclean.presentation.register.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(SignInInteractorImpl())
        presenter.attachView(this)

        login_btn.setOnClickListener {
            signIn()
        }

        login_passwordrecover_txtv.setOnClickListener {
            navigateToPasswordRecover()
        }

        login_createNewAccount_txtv.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun showError(msgError: String?) {
        toast(msgError)
    }

    override fun showProgressBar() {
        login_progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        login_progressBar.visibility = View.GONE
    }

    override fun signIn() {
        //E-mail without space bar
        val email = login_email_edtxt.text.toString().trim()
        val password = login_password_edtxt.text.toString().trim()
        if (presenter.checkEmptyFields(email, password)){
            toast("Campos vacios")
        } else {
            Log.d("Login:", "1")
            presenter.signInUserWithEmailAndPassword(email, password)

        }

    }

    override fun navigateToMain() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToPasswordRecover() {
        val intent = Intent(this, PasswordRecoverActivity::class.java)
        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }
}