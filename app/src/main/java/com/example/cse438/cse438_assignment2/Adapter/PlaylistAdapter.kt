package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.SongActivity
import com.example.cse438.cse438_assignment2.TracksInAPlaylist
import com.example.cse438.cse438_assignment2.database.Playlist

class PlaylistViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_layout, parent, false)) {
    private val nameToDisplay : TextView
    private val descriptionToDisplay : TextView

    private val playlistName: TextView = itemView.findViewById(R.id.NameToDisplay)

    init {
        nameToDisplay = itemView.findViewById(R.id.NameToDisplay)
        descriptionToDisplay = itemView.findViewById(R.id.DescriptionToDisplay)
    }

    fun bind(playlist: Playlist) {
        nameToDisplay.text = playlist.name
        descriptionToDisplay.text = playlist.description

        //Opens up the playlist (all songs in playlist display)
        playlistName.setOnClickListener {
            val intent = Intent(it.context, TracksInAPlaylist::class.java)
            intent.putExtra("playlistGenre", playlist.genre)
            intent.putExtra("playlistRating", playlist.rating)
            intent.putExtra("playlistName", playlist.name)
            it.context!!.startActivity(intent)
        }

    }

}

//create the listener for the recycler view
class PlaylistListAdapter(private val list: ArrayList<Playlist>?)
    : RecyclerView.Adapter<PlaylistViewHolder>() {
    private var listEvents : ArrayList<Playlist>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlaylistViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val event: Playlist = listEvents!!.get(position)
        holder.bind(event)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}