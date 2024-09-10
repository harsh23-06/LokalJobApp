package com.example.lokaljobapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobapp.R
import com.example.lokaljobapp.model.JobResult

class JobAdapter(
    private val context: Context,
    private val onFavoriteClick: (JobResult, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_JOB = 1
    private val VIEW_TYPE_LOADER = 2

    private val jobs = mutableListOf<JobResult>()
    private var isLoading = false

    fun submitList(newJobs: List<JobResult>) {
        jobs.clear()
        jobs.addAll(newJobs)
        notifyDataSetChanged()
    }


    fun setLoading(loading: Boolean) {
        if (loading && !isLoading) {
            // Add loader at the end
            isLoading = true
            notifyItemInserted(jobs.size)
        } else if (!loading && isLoading) {
            // Remove the loader from the end
            isLoading = false
            notifyItemRemoved(jobs.size)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < jobs.size) VIEW_TYPE_JOB else VIEW_TYPE_LOADER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_JOB) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            JobViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.loader_item, parent, false)
            LoaderViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is JobViewHolder) {
            val job = jobs[position]
            holder.companyName.text = job.company_name ?: "Company Name not available"
            holder.jobTitle.text = job.title
            holder.jobContact.text = job.whatsapp_no
            val primaryDetails = job.primary_details
            if (primaryDetails != null) {
                holder.jobLocation.text = primaryDetails.Place ?: "Location not available"
                holder.jobSalary.text = primaryDetails.Salary ?: "Salary not available"
            } else {
                holder.jobLocation.text = "Location not available"
                holder.jobSalary.text = "Salary not available"
            }

            var isFavorite = false
            fun updateFavoriteIcon() {
                if (isFavorite) {
                    holder.favoriteIcon.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    holder.favoriteIcon.setImageResource(R.drawable.baseline_favorite_border_24)
                }
            }

            updateFavoriteIcon()
            holder.favoriteIcon.setOnClickListener {
                isFavorite = !isFavorite
                updateFavoriteIcon()
                val favoriteValue = if (isFavorite) 1 else 0
                onFavoriteClick(job, favoriteValue)

            }
        }
    }

    override fun getItemCount(): Int = jobs.size + if (isLoading) 1 else 0

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val companyName: TextView = itemView.findViewById(R.id.companyName)
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val jobLocation: TextView = itemView.findViewById(R.id.location)
        val jobSalary: TextView = itemView.findViewById(R.id.salaryRange)
        val jobContact: TextView = itemView.findViewById(R.id.contact)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }

    class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}
