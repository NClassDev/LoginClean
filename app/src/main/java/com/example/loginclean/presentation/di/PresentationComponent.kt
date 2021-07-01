package com.example.loginclean.presentation.di

import com.example.loginclean.presentation.login.view.LoginActivity
import com.example.loginclean.presentation.passwordrecover.view.PasswordRecoverActivity
import com.example.loginclean.presentation.register.view.RegisterActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresentationModule::class])

@Singleton
interface PresentationComponent {
    fun inject(signInActivity: RegisterActivity)
    fun inject(signUpActivity: LoginActivity)
    fun inject(passwordRecoverActivity: PasswordRecoverActivity)
}