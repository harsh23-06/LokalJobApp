package com.example.lokaljobapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobapp.R
import com.example.lokaljobapp.activities.JobInfoActivity
import com.example.lokaljobapp.model.JobResult

class JobAdapter(private val context: Context) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private val jobs = mutableListOf<JobResult>()

    fun submitList(newJobs: List<JobResult>) {
        jobs.clear()
        jobs.addAll(newJobs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]

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
        }
    }

    override fun getItemCount(): Int = jobs.size

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val jobLocation: TextView = itemView.findViewById(R.id.location)
        val jobSalary: TextView = itemView.findViewById(R.id.salaryRange)
        val jobContact: TextView = itemView.findViewById(R.id.contact)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }
}
