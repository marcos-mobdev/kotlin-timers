package br.com.appforge.kotlintimers.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import br.com.appforge.kotlintimers.framework.workers.CustomWork
import br.com.appforge.kotlintimers.databinding.ActivityWorkmanagerBinding
import br.com.appforge.kotlintimers.framework.permissions.PermissionManager
import java.util.concurrent.TimeUnit

class WorkmanagerActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityWorkmanagerBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        PermissionManager.requestPostNotificationsPermission(applicationContext)

        //PeriodicWorkRequest




        /*
        //OneTimeWorkRequest
        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<CustomWork>()
            .setInputData(workDataOf("name" to "Marcos", "time" to 1000))
            .addTag("users") //for tag cancelling

            //Constraints (optional)
            .setConstraints(
                Constraints.Builder()
                    //.setRequiredNetworkType(NetworkType.CONNECTED)
                    //.setRequiresCharging(true)
                    //.setRequiresBatteryNotLow(true)
                    //.setRequiresDeviceIdle(true)
                    .setRequiresStorageNotLow(true)
                    .build()
            )
            .build()
         */

        val periodicTimeWorkRequest = PeriodicWorkRequestBuilder<CustomWork>(
            15, TimeUnit.MINUTES //minimum time
        ).setInitialDelay(1, TimeUnit.SECONDS)
            .build()

        val workManager = WorkManager.getInstance(applicationContext)

        //Info
        workManager
            //.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .getWorkInfoByIdLiveData(periodicTimeWorkRequest.id)
            .observe(this){ workInfo->

                if(workInfo != null){
                    //Log.i("info_scheduling", "Info: $workInfo")
                    if(workInfo.state == WorkInfo.State.RUNNING){}
                    val progressData = workInfo.progress
                    val progress = progressData.getInt("progress",0)
                    Log.i("info_scheduling", "Progress: $progress")
                }
            }

        binding.btnStartWork.setOnClickListener {
            //workManager.enqueue(oneTimeWorkRequest)
            workManager.enqueue(periodicTimeWorkRequest)

            /*
            workManager.enqueueUniqueWork(
                "workUser",
                ExistingWorkPolicy.KEEP,
                oneTimeWorkRequest
            )

             */
        }

        binding.btnCancelWork.setOnClickListener{
            //workManager.cancelWorkById(oneTimeWorkRequest.id)
            workManager.cancelWorkById(periodicTimeWorkRequest.id)
            //workManager.cancelAllWorkByTag("users")
            //workManager.cancelUniqueWork("workUser")
            Log.i("info_scheduling", "Cancelling work...")
            workManager.cancelAllWork() //use carefully
        }


    }
}