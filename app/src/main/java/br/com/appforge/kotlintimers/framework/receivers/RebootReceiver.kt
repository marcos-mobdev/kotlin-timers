package br.com.appforge.kotlintimers.framework.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.appforge.kotlintimers.framework.Scheduling

class RebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Intent.ACTION_BOOT_COMPLETED){
            Log.i("info_scheduling", "System rebooted.")
            val scheduling = Scheduling(context)
            scheduling.scheduleSet()
        }
    }
}