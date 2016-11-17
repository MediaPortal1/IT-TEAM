package ua.com.itteam

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.vk.sdk.VKSdk


class ITTeamApp : Application() {
    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}