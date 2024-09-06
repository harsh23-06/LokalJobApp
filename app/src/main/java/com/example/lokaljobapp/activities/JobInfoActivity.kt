package com.example.lokaljobapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lokaljobapp.R
import com.example.lokaljobapp.databinding.ActivityJobInfoBinding
import com.example.lokaljobapp.model.JobResult

class JobInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJobInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val jobItem = intent.getParcelableExtra<JobResult>("job")
        binding.textView.text = jobItem?.title

    }
}