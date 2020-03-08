package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.SongsViewModel
import com.example.cse438.cse438_assignment2.database.Song

class SongViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.song_layout, parent, false)) {
    private val Song : TextView
    private val Artist : TextView
    private val RemoveSong : Button

    init {
        Song = itemView.findViewById(R.id.SongTitle)
        Artist = itemView.findViewById(R.id.SongArtist)
        RemoveSong = itemView.findViewById(R.id.removeSongButton)
    }

    fun bind(song: Song, viewModel : SongsViewModel) {
        Song.text = song.songTitle
        Artist.text = song.songArtist

        RemoveSong.setOnClickListener {
            viewModel.clear(song.id)
        }

    }

}


//create the listener for the recycler view
class SongAdapter(private val list: ArrayList<Song>?, private val viewModel : SongsViewModel)
    : RecyclerView.Adapter<SongViewHolder>() {
    private var listEvents : ArrayList<Song>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val event: Song = listEvents!!.get(position)
        holder.bind(event, viewModel)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}