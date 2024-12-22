package br.com.appforge.kotlintimers.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import br.com.appforge.kotlintimers.R
import br.com.appforge.kotlintimers.databinding.ActivityRequestsBinding
import br.com.appforge.kotlintimers.framework.permissions.PermissionManager
import br.com.appforge.kotlintimers.framework.workers.APIWork
import br.com.appforge.kotlintimers.framework.workers.CustomWork
import java.util.concurrent.TimeUnit

class RequestsActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityRequestsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        PermissionManager.requestPostNotificationsPermission(applicationContext)

        //PeriodicWorkRequest
        val periodicTimeWorkRequest = PeriodicWorkRequestBuilder<APIWork>(
            1, TimeUnit.DAYS //minimum time
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
                    //if(workInfo.state == WorkInfo.State.RUNNING){}
                    val progressData = workInfo.progress
                    val progress = progressData.getInt("progress",0)
                    Log.i("info_scheduling", "Progress: $progress")
                }
            }

        binding.btnStartRequest.setOnClickListener {
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

        binding.btnCancelRequest.setOnClickListener{
            //workManager.cancelWorkById(oneTimeWorkRequest.id)
            workManager.cancelWorkById(periodicTimeWorkRequest.id)
            //workManager.cancelAllWorkByTag("users")
            //workManager.cancelUniqueWork("workUser")
            Log.i("info_scheduling", "Cancelling work...")
            //workManager.cancelAllWork() //use carefully
        }
    }
}