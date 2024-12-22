package br.com.appforge.kotlintimers.framework

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import br.com.appforge.kotlintimers.utils.Constants

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    private fun createChannel(){
        val idChannel = Constants.CHANNEL_ID
        val notificationManager =
            applicationContext.getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(idChannel, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
    }


}