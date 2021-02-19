package com.cskapp.contentproviderwithroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProgressBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)
        supportFragmentManager.beginTransaction().replace(R.id.container, ProgressFragment()).commit()
    }
}