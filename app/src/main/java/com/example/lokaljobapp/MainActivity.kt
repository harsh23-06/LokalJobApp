package com.example.lokaljobapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lokaljobapp.databinding.ActivityMainBinding
import com.example.lokaljobapp.fragments.BookmarkFragment
import com.example.lokaljobapp.fragments.JobListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        if (savedInstanceState == null)
            replaceFragment(JobListFragment())

        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.savedJobs -> {
                    replaceFragment(BookmarkFragment())
                    true
                }

                R.id.jobList -> {
                    replaceFragment(JobListFragment())
                    true
                }

                else -> {
                    false
                }
            }

        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val fragManager = supportFragmentManager
        val fragmentTransaction = fragManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}