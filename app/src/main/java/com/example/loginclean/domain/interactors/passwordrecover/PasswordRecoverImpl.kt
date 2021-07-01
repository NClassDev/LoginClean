package com.example.loginclean.domain.interactors.passwordrecover

import com.example.loginclean.presentation.passwordrecover.exception.PasswordRecoverException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class PasswordRecoverImpl: PasswordRecoverInteractor {

    override suspend fun sendPasswordResetEmail(email: String): Unit = suspendCancellableCoroutine { continuation ->
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(PasswordRecoverException(it.exception?.message !!))
            }
        }
    }

}