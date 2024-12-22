package br.com.appforge.kotlintimers.framework.workers

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import br.com.appforge.kotlintimers.utils.Constants
import br.com.appforge.kotlintimers.R
import kotlinx.coroutines.delay

class CustomWork(private val appContext: Context, private val params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        startAction()
        return Result.success(workDataOf("return" to 200))
    }

    private suspend fun startAction(){

        setForeground(
            ForegroundInfo(
                System.currentTimeMillis().toInt(),
                NotificationCompat.Builder(applicationContext, Constants.CHANNEL_ID).apply {
                    setSmallIcon(R.drawable.ic_notification_24)
                    setShowWhen(true)
                    setContentTitle("Sao Paulo Times")
                    setContentText("Check our last news!")
                }.build()

            )
        )

        val name = params.inputData.getString("name")
        val time = params.inputData.getInt("time", 0)

        setProgress(workDataOf("progress" to 0))

        Log.i("info_scheduling", "name: $name, time: $time")

        repeat(10){ counter->
            delay(1000)
            setProgress(workDataOf("progress" to counter))
            //Log.i("info_scheduling", "starting work: $counter")
        }
    }
}