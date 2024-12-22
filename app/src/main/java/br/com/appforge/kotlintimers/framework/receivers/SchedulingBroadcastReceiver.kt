package br.com.appforge.kotlintimers.framework.receivers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import br.com.appforge.kotlintimers.R

class SchedulingBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("info_scheduling", "Scheduling broadcast!")
        showNotification(context)
    }

    private fun showNotification(context:Context){

        val idChannel = "reminders"
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(idChannel,"Reminders", NotificationManager.IMPORTANCE_HIGH )
            notificationManager.createNotificationChannel(channel)
        }


        val notificationBuilder = NotificationCompat.Builder(
            context,
            idChannel
        ).apply {
            setSmallIcon(R.drawable.ic_notification_24)
            setShowWhen(true)
            setContentText("Reminder")
            setContentText("It's time to study!")
        }

        notificationManager.notify(0,notificationBuilder.build())

    }
}