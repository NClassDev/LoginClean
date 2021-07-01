package com.example.loginclean.domain.interactors.registerinteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterInteractorImpl: RegisterInterator {
    override fun signUp(
        fullname: String,
        email: String,
        password: String,
        listener: RegisterInterator.RegisterCallback
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { itSignup ->
            itSignup
            if(itSignup.isSuccessful){

                val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(fullname).build()

                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        listener.onRegisterSuccess()
                    }
                }

            }else{
                listener.onRegisterFailure(itSignup.exception?.message.toString())
            }
        }

    }
}