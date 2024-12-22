package br.com.appforge.kotlintimers.framework.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import br.com.appforge.kotlintimers.utils.Constants
import br.com.appforge.kotlintimers.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SchedulingService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationBuilder = NotificationCompat.Builder(
            applicationContext,
            Constants.CHANNEL_ID
        ).apply {
            setSmallIcon(R.drawable.ic_notification_24)
            setShowWhen(true)
            setContentText("Reminder")
            setContentText("It's time to study!")
        }

        startForeground(1, notificationBuilder.build())

        myAction()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun myAction() {
        coroutineScope.launch {
            repeat(12) { counter ->
                delay(2000)
                Log.i("info_scheduling", "Service called by alarm...")
            }
        }
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}