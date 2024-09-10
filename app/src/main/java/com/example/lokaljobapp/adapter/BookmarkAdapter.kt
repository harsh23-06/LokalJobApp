package com.example.lokaljobapp.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lokaljobapp.R
import com.example.lokaljobapp.db.JobEntity

class BookmarkAdapter(
    private val context: Context
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val bookmarkJobs = mutableListOf<JobEntity>()

    fun submitList(newFavoriteJobs: List<JobEntity>) {
        bookmarkJobs.clear()
        bookmarkJobs.addAll(newFavoriteJobs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val job = bookmarkJobs[position]
        holder.companyName.text = job.companyName ?: "Company Name not available"
        holder.jobTitle.text = job.title
        holder.jobContact.text = job.contact
        holder.jobLocation.text = job.location
        holder.jobSalary.text=job.salary


        holder.bookmarkIcon.setImageResource(R.drawable.baseline_bookmark_24) // Always show as bookmark
    }

    override fun getItemCount(): Int = bookmarkJobs.size

    class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val companyName: TextView = itemView.findViewById(R.id.companyName)
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val jobLocation: TextView = itemView.findViewById(R.id.location)
        val jobSalary: TextView = itemView.findViewById(R.id.salaryRange)
        val jobContact: TextView = itemView.findViewById(R.id.contact)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }
}
