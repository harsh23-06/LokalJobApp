package com.example.lokaljobapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokaljobapp.adapter.BookmarkAdapter
import com.example.lokaljobapp.databinding.FragmentBookmarkBinding
import com.example.lokaljobapp.db.JobDatabase
import com.example.lokaljobapp.repository.JobRepository
import com.example.lokaljobapp.viewModel.FavoritesViewModel
import com.example.lokaljobapp.viewModel.FavoritesViewModelFactory


class BookmarkFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val jobDao = JobDatabase.getDatabase(requireContext()).jobDao()
        val repository = JobRepository(jobDao)
        val db = JobDatabase.getDatabase(requireContext()).jobDao()
        val favoriteJobs = db.getFavoriteJobs().value
        Log.d("DatabaseContent", "Favorite jobs in database: $favoriteJobs")
        val viewModelFactory = FavoritesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)

        bookmarkAdapter = BookmarkAdapter(requireContext())
        binding.favouriteLIst.apply {
            adapter = bookmarkAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.favoriteJobs.observe(viewLifecycleOwner) { favoriteJobs ->
            Log.d("BookmarkFragment", "Favorite jobs received: $favoriteJobs")
            bookmarkAdapter.submitList(favoriteJobs)
        }
        return binding.root
    }
}