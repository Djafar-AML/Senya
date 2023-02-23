package com.example.senya

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

lateinit var application: SenyaApplictaion
    private set

@HiltAndroidApp
class SenyaApplictaion : Application() {

    override fun onCreate() {
        super.onCreate()
        initApplication()
    }

    private fun initApplication() {
        application = this
    }
}