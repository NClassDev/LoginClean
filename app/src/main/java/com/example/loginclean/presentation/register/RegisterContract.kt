package com.example.loginclean.presentation.register

interface RegisterContract {

    interface View {
        fun navigateToMain()
        fun signUp()
        fun showProgress()
        fun hideProgress()
        fun showError(errorMsg: String)
    }

    interface Presenter{
        fun attachView(view: View)
        fun dettachView()
        fun isViewAttached(): Boolean
        fun checkEmptyName(fullName:String) : Boolean
        fun checkEmptyPasswords(pw1:String, pw2:String) : Boolean
        fun checkMatchPasswords(pw1:String, pw2:String) : Boolean
        fun checkValidEmail(email: String): Boolean
        fun signUp(fullName: String, email: String, password:String)

    }
}