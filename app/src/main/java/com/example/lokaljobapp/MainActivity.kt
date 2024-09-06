package com.example.lokaljobapp

import android.app.Fragment
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lokaljobapp.api.JobListService
import com.example.lokaljobapp.api.RetrofitInstance
import com.example.lokaljobapp.databinding.ActivityMainBinding
import com.example.lokaljobapp.fragments.BookmarkFragment
import com.example.lokaljobapp.fragments.JobListFragment
import com.example.lokaljobapp.model.JobResponse
import com.example.lokaljobapp.repository.JobRepository
import com.example.lokaljobapp.util.Resource
import com.example.lokaljobapp.viewModel.JobsViewModel
import com.example.lokaljobapp.viewModel.JobsViewModelFactory

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