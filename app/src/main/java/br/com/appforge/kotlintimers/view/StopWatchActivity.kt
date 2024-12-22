package br.com.appforge.kotlintimers.view

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.kotlintimers.databinding.ActivityStopWatchBinding
import java.util.Locale

class StopWatchActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityStopWatchBinding.inflate(layoutInflater)
    }

    private lateinit var countDownTimer: CountDownTimer
    private var isRunningStopwatch = false
    private var timeMilliseconds = 60000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStartStopwatch.setOnClickListener {
            if(isRunningStopwatch){
                stopStopwatch()
            }else{
                val timeTyped = binding.editTime.text.toString()
                timeMilliseconds = timeTyped.toLong() *60_000
                startStopwatch(timeMilliseconds)
            }
        }

        binding.btnResetStopwatch.setOnClickListener {
            resetStopwatch()
        }
    }

    private fun resetStopwatch() {
        timeMilliseconds = 60_000
        binding.textTime.text = "00:00"
        binding.btnResetStopwatch.visibility = View.GONE
    }

    private fun startStopwatch(timeMillisecondsParam:Long) {
        isRunningStopwatch = true
        countDownTimer = object :CountDownTimer(timeMillisecondsParam, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                timeMilliseconds = millisUntilFinished
                updateInterface()
            }

            override fun onFinish() {
                binding.textTime.text = "Finished"
            }

        }
        countDownTimer.start()
        binding.btnStartStopwatch.text = "Stop"
        binding.btnResetStopwatch.visibility = View.GONE

    }

    private fun updateInterface() {
        val minutes = (timeMilliseconds/1000) / 60
        val seconds = (timeMilliseconds/1000) % 60 //0 to 59

        binding.textTime.text = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds)
    }

    private fun stopStopwatch() {
        isRunningStopwatch = false
        binding.btnStartStopwatch.text = "Start"
        binding.btnResetStopwatch.visibility = View.VISIBLE
        countDownTimer.cancel()
    }

    override fun onStop() {
        super.onStop()
        countDownTimer.cancel()
    }


}