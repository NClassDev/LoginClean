package com.example.loginclean.domain.interactors.logininteractor

import com.example.loginclean.presentation.exceptions.FirebaseLoginException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SignInInteractorImpl : SignInInteractor {

    suspend override fun signInWithEmailAndPassword(
        email: String,
        password: String): Unit = suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    continuation.resume(Unit)
                }else{
                    continuation.resumeWithException(FirebaseLoginException(it.exception?.message))
                }
            }

        }
    }
