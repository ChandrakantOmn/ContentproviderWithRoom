package com.cskapp.contentproviderwithroom

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_progress.*


class ProgressFragment : Fragment() {
    var counter = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val timer = object : CountDownTimer(50*1000, 500) {
            override fun onTick(millisUntilFinished: Long) {
                counter++
                textCounter.text = counter.toString()
                progressBar.progress = counter
            }

            override fun onFinish() {
                textCounter.text = "Task completed"
               // progressBar.progress = 0
            }
        }
        progressBar.progress = 0;
        timer.start()
    }

}