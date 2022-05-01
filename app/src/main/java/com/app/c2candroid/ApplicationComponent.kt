package com.app.c2candroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationComponent: Application(){
    override fun onCreate() {
        super.onCreate()

    }
}
