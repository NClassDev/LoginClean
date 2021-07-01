package com.example.loginclean.domain.interactors.registerinteractor

interface RegisterInterator {

    interface RegisterCallback {

        fun onRegisterSuccess()
        fun onRegisterFailure(errorMsg: String)
    }

 fun signUp(fullname: String, email:String, password:String, listener:RegisterCallback)
}