package br.com.appforge.kotlintimers.framework

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import br.com.appforge.kotlintimers.framework.receivers.SchedulingBroadcastReceiver
import br.com.appforge.kotlintimers.framework.services.SchedulingService
import java.util.Calendar

class Scheduling(private val context: Context) {
    private lateinit var alarmManager: AlarmManager

    private lateinit var pendingIntent: PendingIntent

    fun scheduleSet() {

        val intent = Intent(context, SchedulingBroadcastReceiver::class.java)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            //set(2025,4-1,30) //month: 0 for January, 1 for February, 2 for March...
            //set(Calendar.YEAR,2025)
            //set(Calendar.MONTH,0)
            //set(Calendar.HOUR_OF_DAY,0)
            //add(Calendar.YEAR,1)
            //add(Calendar.MINUTE,10)
            add(Calendar.SECOND, 10)
        }
        pendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.set(
            AlarmManager.RTC,
            calendar.timeInMillis,
            pendingIntent

        )
    }

    fun scheduleInexactRepeating() {
        val intent = Intent(context, SchedulingBroadcastReceiver::class.java)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }

        val calendarInterval = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        }
        pendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC,
            calendar.timeInMillis,
            //calendarInterval.timeInMillis,
            10_000,
            pendingIntent
        )

    }

    fun schedulingService() {
        val intent = Intent(context, SchedulingService::class.java)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PendingIntent.getForegroundService(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getService(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }

        alarmManager = context.getSystemService(AlarmManager::class.java)

        alarmManager.set(
            AlarmManager.RTC,
            calendar.timeInMillis + 5000,
            pendingIntent

        )
    }


    fun cancel() {
        alarmManager.cancel(pendingIntent)
        Log.i("info_scheduling", "Cancelling intent.")
    }
}