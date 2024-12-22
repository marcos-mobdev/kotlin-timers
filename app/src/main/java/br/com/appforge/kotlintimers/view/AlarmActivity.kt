package br.com.appforge.kotlintimers.view

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import br.com.appforge.kotlintimers.framework.Scheduling
import br.com.appforge.kotlintimers.databinding.ActivityAlarmBinding
import br.com.appforge.kotlintimers.framework.permissions.PermissionManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityAlarmBinding.inflate(layoutInflater)
    }

    private val scheduling by lazy{
        Scheduling(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        PermissionManager.requestPostNotificationsPermission(this)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            //set(2025,4-1,30) //month: 0 for January, 1 for February, 2 for March...
            //set(Calendar.YEAR,2025)
            //set(Calendar.MONTH,0)
            //set(Calendar.HOUR_OF_DAY,0)
            //add(Calendar.YEAR,1)
            //add(Calendar.MINUTE,10)
            add(Calendar.SECOND,10)
        }
        val dateInMilliseconds = calendar.timeInMillis
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH)
        val formattedDate = formatter.format(dateInMilliseconds)

        //Log.i("info_alarm", formattedDate)

        binding.btnScheduleAlarm.setOnClickListener {
            scheduling.scheduleSet()
        }

        binding.btnScheduleRepeat.setOnClickListener {
            scheduling.scheduleInexactRepeating()
        }

        binding.btnScheduleService.setOnClickListener {
            scheduling.schedulingService()
        }

        binding.btnCancelAlarm.setOnClickListener {
            scheduling.cancel()
        }
    }
}