package com.example.loginclean.presentation.di

import com.example.loginclean.domain.interactors.logininteractor.SignInInteractor
import com.example.loginclean.domain.interactors.logininteractor.SignInInteractorImpl
import com.example.loginclean.domain.interactors.passwordrecover.PasswordRecoverImpl
import com.example.loginclean.domain.interactors.passwordrecover.PasswordRecoverInteractor
import com.example.loginclean.domain.interactors.registerinteractor.RegisterInteractorImpl
import com.example.loginclean.domain.interactors.registerinteractor.RegisterInterator
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideSignInInteractor(): SignInInteractor = SignInInteractorImpl()

    @Provides
    fun providePasswordRecoverInteractor(): PasswordRecoverInteractor = PasswordRecoverImpl()

    @Provides
    fun provideSignUpInteractor(): RegisterInterator = RegisterInteractorImpl()
}