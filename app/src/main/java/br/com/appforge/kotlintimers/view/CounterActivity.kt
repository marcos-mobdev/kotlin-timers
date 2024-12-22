package br.com.appforge.kotlintimers.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.kotlintimers.databinding.ActivityCounterBinding
import java.util.Timer
import java.util.TimerTask

class CounterActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCounterBinding.inflate(layoutInflater)
    }
    private lateinit var timer: Timer
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            timer = Timer()
            timer.schedule(
                object: TimerTask(){
                override fun run() {
                    runOnUiThread {
                        binding.countdownTextview.text = counter.toString()
                    }
                    counter++
                }
                },
                1000,1000
            )
        }

        binding.btnStop.setOnClickListener {
            binding.countdownTextview.text = "Done"
            counter = 0
            timer.cancel()
        }

    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }
}