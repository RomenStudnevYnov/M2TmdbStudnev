package com.example.m2tmdbstudnev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m2tmdbstudnev.model.Person
import com.squareup.picasso.Picasso

const val IMAGE_URL = "https://image.tmdb.org/t/p/w45"

class PeopleAdapter(private val peopleData: List<Person>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val knownFor: TextView = view.findViewById(R.id.known_for)
        val popularity: TextView = view.findViewById(R.id.popularity)
        val portrait: ImageView = view.findViewById(R.id.portrait)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.person_card,
            parent,
            false,
        )
        return ViewHolder(view)
    }

    override fun getItemCount() = this.peopleData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = peopleData[position]
        holder.name.text = person.name
        holder.knownFor.text = person.knownForDepartment
        holder.popularity.text = person.popularity.toString()
        Picasso.get()
            .load("$IMAGE_URL${person.profilePath}")
            .placeholder(android.R.drawable.progress_horizontal)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.portrait)
    }

}
