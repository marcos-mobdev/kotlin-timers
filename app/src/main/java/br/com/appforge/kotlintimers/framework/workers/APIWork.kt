package br.com.appforge.kotlintimers.framework.workers

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import br.com.appforge.kotlintimers.R
import br.com.appforge.kotlintimers.api.RetrofitService
import br.com.appforge.kotlintimers.utils.Constants
import kotlinx.coroutines.delay

class APIWork(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return startRequests()
    }

    private suspend fun startRequests():Result{
        setForeground(
            ForegroundInfo(
                System.currentTimeMillis().toInt(),
                NotificationCompat.Builder(applicationContext, Constants.CHANNEL_ID).apply {
                    setSmallIcon(R.drawable.ic_notification_24)
                    setShowWhen(true)
                    setContentTitle("Reminder")
                    setContentText("Time to study!")
                }.build()

            )
        )

        val jsonPlaceAPI = RetrofitService.jsonPlaceApi
        val response = jsonPlaceAPI.getPosts()

        if(response.isSuccessful){
            response.body().let { postList->
                postList?.forEach { post->
                    delay(1000)
                    Log.i("info_scheduling", "${post.id} - ${post.title}")
                }
            }
            return Result.success()
        }else{
            if(response.code().toString().startsWith("5")){
                return Result.retry()
            }
            return Result.failure()
        }
    }
}