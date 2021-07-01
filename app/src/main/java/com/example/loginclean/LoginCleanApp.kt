package com.example.loginclean

import android.app.Application
import com.example.loginclean.presentation.di.DaggerPresentationComponent
import com.example.loginclean.presentation.di.PresentationComponent
import com.example.loginclean.presentation.di.PresentationModule

class LoginCleanApp: Application() {

    private var appComponent: PresentationComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerPresentationComponent
            .builder()
            .presentationModule(PresentationModule())
            .build()
    }

    fun getAppComponent(): PresentationComponent? = appComponent

}