package com.example.loginclean.domain.interactors.logininteractor

interface SignInInteractor {

    suspend fun signInWithEmailAndPassword(email:String, password:String)

}