package com.example.loginclean.presentation.passwordrecover.presenter

import com.example.loginclean.domain.interactors.passwordrecover.PasswordRecoverInteractor
import com.example.loginclean.presentation.passwordrecover.PasswordRecoverContract
import com.example.loginclean.presentation.passwordrecover.exception.PasswordRecoverException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import javax.inject.Inject

class PasswordRecoverPresenter @Inject constructor (private val passwordRecoverInteractor: PasswordRecoverInteractor) : PasswordRecoverContract.PasswordRecoverPresenter, CoroutineScope{

    var view: PasswordRecoverContract.PasswordRecoverView? = null

    var job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun attachView(passwordRecoverView: PasswordRecoverContract.PasswordRecoverView) {
        this.view = passwordRecoverView
    }

    override fun detachView() {
        view = null
    }

    override fun detachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return view != null

    }

    override fun sendPasswordRecover(email: String) {
        launch {
            try {
                view?.showProgress()
                passwordRecoverInteractor.sendPasswordResetEmail(email)
                view?.hideProgress()
                view?.navigateToLogin()
            } catch (e: PasswordRecoverException){
                view?.hideProgress()
                view?.showError(e.message)
            }
        }
    }


}