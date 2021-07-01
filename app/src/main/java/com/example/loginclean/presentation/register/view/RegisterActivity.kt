package com.example.loginclean.presentation.register.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.loginclean.R
import com.example.loginclean.base.BaseActivity
import com.example.loginclean.domain.interactors.registerinteractor.RegisterInteractorImpl
import com.example.loginclean.presentation.login.view.LoginActivity
import com.example.loginclean.presentation.register.RegisterContract
import com.example.loginclean.presentation.register.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.View {

    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RegisterPresenter(RegisterInteractorImpl())
        presenter.attachView(this)

        signup_btn.setOnClickListener {
            signUp()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun navigateToMain() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun signUp() {
        val fullname = signup_name_edtxt.text.toString().trim()
        val email = signup_email_edtxt.text.toString().trim()
        val password1 = signup_password1_edtxt.text.toString().trim()
        val password2 = signup_password2_edtxt.text.toString().trim()

        if(presenter.checkEmptyName(fullname)){
            signup_name_edtxt.error = "The name is empty"
            return
        }

        if(!presenter.checkValidEmail(email)){
            signup_email_edtxt.error = "The email is invalid"
            return
        }

        if(presenter.checkEmptyPasswords(password1, password2)){
            signup_password1_edtxt.error = "Empty field"
            signup_password2_edtxt.error = "Empty field"
            return
        }

        if(!presenter.checkMatchPasswords(password1, password2)){
            signup_password1_edtxt.error = "Passwords don't match"
            signup_password2_edtxt.error = "Passwords don't match"
            return
        }

        presenter.signUp(fullname, email, password1)
    }

    override fun showProgress() {
        signup_progressBar.visibility = View.VISIBLE
        signup_btn.visibility = View.GONE

    }

    override fun hideProgress() {
        signup_progressBar.visibility = View.GONE
        signup_progressBar.visibility = View.VISIBLE
    }

    override fun showError(errorMsg: String) {
        toast(errorMsg)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
    }
}