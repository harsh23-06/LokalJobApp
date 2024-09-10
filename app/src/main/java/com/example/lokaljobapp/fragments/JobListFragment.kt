package com.example.lokaljobapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobapp.adapter.JobAdapter
import com.example.lokaljobapp.R
import com.example.lokaljobapp.databinding.FragmentJobListBinding
import com.example.lokaljobapp.db.JobDatabase
import com.example.lokaljobapp.repository.JobRepository
import com.example.lokaljobapp.viewModel.JobsViewModel
import com.example.lokaljobapp.viewModel.JobsViewModelFactory

class JobListFragment : Fragment() {

    private lateinit var viewModel: JobsViewModel
    private lateinit var jobAdapter: JobAdapter
    private var isLoading = false

    private lateinit var binding: FragmentJobListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_list, container, false)
        val jobDao = JobDatabase.getDatabase(requireContext()).jobDao()

        // Create the repository
        val repository = JobRepository(jobDao)

        // Create ViewModelFactory and ViewModel
        val viewModelFactory = JobsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(JobsViewModel::class.java)

        val jobAdapter = JobAdapter(requireContext()) { job, isFavorite ->
            viewModel.updateJobFavorite(job, isFavorite)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.jobListView)
        recyclerView.adapter = jobAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Implement Pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading && viewModel.hasMoreItems &&
                    pastVisibleItems + visibleItemCount >= totalItemCount) {

                    // Show loader and load more jobs
                    jobAdapter.setLoading(true)
                    viewModel.loadJobs()
                }
            }
        })

        viewModel.jobList.observe(viewLifecycleOwner, { jobs ->
            jobAdapter.submitList(jobs)
        })

        viewModel.loadJobs()

        return view
    }
}