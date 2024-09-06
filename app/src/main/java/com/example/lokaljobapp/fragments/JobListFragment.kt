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
import com.example.lokaljobapp.repository.JobRepository
import com.example.lokaljobapp.viewModel.JobsViewModel
import com.example.lokaljobapp.viewModel.JobsViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JobListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JobListFragment : Fragment() {

    private lateinit var viewModel: JobsViewModel
    private lateinit var jobAdapter: JobAdapter
    private lateinit var binding: FragmentJobListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_job_list, container, false)

        // Create the repository
        val repository = JobRepository()

        // Create ViewModelFactory and ViewModel
        val viewModelFactory = JobsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(JobsViewModel::class.java)

        jobAdapter = JobAdapter(requireContext())
        val recyclerView = view.findViewById<RecyclerView>(R.id.jobListView)
        recyclerView.adapter = jobAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Implement Pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1) {
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