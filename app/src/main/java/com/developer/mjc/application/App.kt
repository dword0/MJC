package com.developer.mjc.application

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        //Initialize Prefs class
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}