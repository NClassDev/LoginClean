package com.example.loginclean.presentation.register.presenter

import androidx.core.util.PatternsCompat
import com.example.loginclean.domain.interactors.registerinteractor.RegisterInterator
import com.example.loginclean.presentation.register.RegisterContract

class RegisterPresenter(registerInterator: RegisterInterator) : RegisterContract.Presenter {

    var view: RegisterContract.View? = null
    var registerInterator: RegisterInterator? = null

    init {
        this.registerInterator = registerInterator
    }

    override fun attachView(view: RegisterContract.View) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun checkEmptyName(fullName: String): Boolean {
        return fullName.isEmpty()
    }

    override fun checkEmptyPasswords(pw1: String, pw2: String): Boolean {
        return pw1.isEmpty() or pw2.isEmpty()
    }

    override fun checkMatchPasswords(pw1: String, pw2: String): Boolean {
        return pw1.equals(pw2)
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun signUp(fullName: String, email: String, password: String) {
        registerInterator?.signUp(fullName, email, password, object: RegisterInterator.RegisterCallback{
            override fun onRegisterSuccess() {
                view?.navigateToMain()
                view?.hideProgress()
            }
            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgress()

            }
        })
    }
}