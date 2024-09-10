package com.example.lokaljobapp.fragments

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobapp.adapter.JobAdapter
import com.example.lokaljobapp.databinding.FragmentJobListBinding
import com.example.lokaljobapp.db.JobDatabase
import com.example.lokaljobapp.repository.JobRepository
import com.example.lokaljobapp.util.NetworkChangeReceiver
import com.example.lokaljobapp.util.NetworkUtils
import com.example.lokaljobapp.viewModel.JobsViewModel
import com.example.lokaljobapp.viewModel.JobsViewModelFactory

class JobListFragment : Fragment() {

    private lateinit var viewModel: JobsViewModel
    private lateinit var jobAdapter: JobAdapter
    private var isLoading = false

    private lateinit var binding: FragmentJobListBinding
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobListBinding.inflate(inflater, container, false)

        val jobDao = JobDatabase.getDatabase(requireContext()).jobDao()
        val repository = JobRepository(jobDao)
        val viewModelFactory = JobsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(JobsViewModel::class.java)

        jobAdapter = JobAdapter(requireContext()) { job, isFavorite ->
            viewModel.updateJobFavorite(job, isFavorite)
        }

        binding.jobListView.adapter = jobAdapter
        binding.jobListView.layoutManager = LinearLayoutManager(context)

        // Implement Pagination
        binding.jobListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.jobListView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading && viewModel.hasMoreItems &&
                    pastVisibleItems + visibleItemCount >= totalItemCount) {
                    jobAdapter.setLoading(true)
                    viewModel.loadJobs()
                }
            }
        })

        // Initialize NetworkChangeReceiver
        networkChangeReceiver = NetworkChangeReceiver { isConnected ->
            if (isConnected) {
                binding.jobListView.visibility = View.VISIBLE
                binding.noConnectionImageView.visibility = View.GONE
                viewModel.loadJobs() // Reload jobs when internet is available
            } else {
                binding.jobListView.visibility = View.GONE
                binding.noConnectionImageView.visibility = View.VISIBLE
                Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
            }
        }

        // Register NetworkChangeReceiver
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireContext().registerReceiver(networkChangeReceiver, intentFilter)

        // Initial network check
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            binding.jobListView.visibility = View.VISIBLE
            binding.noConnectionImageView.visibility = View.GONE
            viewModel.loadJobs()
        } else {
            binding.jobListView.visibility = View.GONE
            binding.noConnectionImageView.visibility = View.VISIBLE
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }

        viewModel.jobList.observe(viewLifecycleOwner, { jobs ->
            jobAdapter.submitList(jobs)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Unregister the receiver
        requireContext().unregisterReceiver(networkChangeReceiver)
    }
}
