package com.example.nextdoormvvm.internal

import android.app.Application
import android.content.Context

class NextDoorApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        var context: Context? = null
    }
}