package com.example.loginclean.domain.interactors.passwordrecover

interface PasswordRecoverInteractor {
    suspend fun sendPasswordResetEmail(email: String)

}