package com.example.loginclean.presentation.login.presenter

import com.example.loginclean.domain.interactors.logininteractor.SignInInteractor
import com.example.loginclean.presentation.exceptions.FirebaseLoginException
import com.example.loginclean.presentation.login.LoginContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginPresenter(signInInteractor: SignInInteractor) : LoginContract.Presenter, CoroutineScope {

    var view: LoginContract.View? = null

    var signInInteractor: SignInInteractor? = null

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    init {
        this.signInInteractor = signInInteractor
    }

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return view != null

    }

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        launch {
            view?.showProgressBar()

            try {
                signInInteractor?.signInWithEmailAndPassword(email, password)

                if (isViewAttached()) {
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            } catch (e: FirebaseLoginException) {
                view?.showError(e.message)
                view?.hideProgressBar()
            }


        }
    }

    override fun checkEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty()
    }


}