package com.example.loginclean.presentation.login

interface LoginContract {

    //Metodos con inpacto en la vista
    interface View{
        fun showError(msgError: String?)
        fun showProgressBar()
        fun hideProgressBar()
        fun signIn()
        fun navigateToMain()
        fun navigateToPasswordRecover()
        fun navigateToRegister()
    }

    interface Presenter{
        fun attachView(view: View)
        fun dettachView()
        fun dettachJob()
        fun isViewAttached(): Boolean
        fun signInUserWithEmailAndPassword(email:String, password:String)
        fun checkEmptyFields(email: String, password: String):Boolean
    }


}