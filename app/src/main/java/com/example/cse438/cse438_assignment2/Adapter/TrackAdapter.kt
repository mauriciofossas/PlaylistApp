package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.FullTrack
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Fragments.TracksFragment
import com.example.cse438.cse438_assignment2.MainActivity
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.SongActivity
import com.example.cse438.cse438_assignment2.TrackViewModel
import com.squareup.picasso.Picasso


class TrackViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.track_layout, parent, false)) {

    private val trackNameView: TextView = itemView.findViewById(R.id.a_track)
    private val trackImage: ImageView = itemView.findViewById(R.id.anImage)

    fun bind(track: Track) {
        trackNameView.text = track.title
        Picasso.get().load(track.album.cover).into(trackImage)
        trackImage.tag = track.id

        trackImage.setOnClickListener {

            val intent = Intent(it.context, SongActivity::class.java)
            //intent.putExtra("title", fullTrack!!.title)
            intent.putExtra("id", track.id)
            it.context!!.startActivity(intent)
        }
    }
}


class TrackListAdapter(private val list: ArrayList<Track>) : RecyclerView.Adapter<TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrackViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track: Track = list[position]
        holder.bind(track)
    }

    override fun getItemCount(): Int = list.size
}