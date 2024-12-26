package br.com.appforge.kotlintimers.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.kotlintimers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnCounter.setOnClickListener {
                startActivity(Intent(applicationContext, CounterActivity::class.java))
            }
            btnStopwatch.setOnClickListener {
                startActivity(Intent(applicationContext, StopWatchActivity::class.java))
            }
            btnAlarm.setOnClickListener {
                startActivity(Intent(applicationContext, AlarmActivity::class.java))
            }
            btnWorkmanager.setOnClickListener {
                startActivity(Intent(applicationContext, WorkmanagerActivity::class.java))
            }
            btnRequests.setOnClickListener {
                startActivity(Intent(applicationContext, RequestsActivity::class.java))
            }
        }

    }
}