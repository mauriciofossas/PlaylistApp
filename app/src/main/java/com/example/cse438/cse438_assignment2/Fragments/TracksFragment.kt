package com.example.cse438.cse438_assignment2.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.TrackListAdapter
import com.example.cse438.cse438_assignment2.Data.FullTrack
import com.example.cse438.cse438_assignment2.Data.Track

import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.SongActivity
import com.example.cse438.cse438_assignment2.TrackViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tracks.*
import kotlinx.android.synthetic.main.track_layout.*
import java.lang.Exception

class TracksFragment : Fragment() {

    lateinit var viewModel: TrackViewModel

    var songList: ArrayList<Track> = ArrayList()

    var previousArtist : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tracks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TrackViewModel::class.java)

        var trackAdapter = TrackListAdapter(songList)
        recyclerView.adapter = trackAdapter


        recyclerView.layoutManager = GridLayoutManager(this.context, 2)

        previousArtist = activity!!.intent.getStringExtra("previous")
        if(previousArtist == null || previousArtist == ""){
            viewModel.getTopTracks()

            viewModel!!.songList.observe(this, Observer {
                songList.clear()
                songList.addAll(it.tracks.data)
                trackAdapter.notifyDataSetChanged()
            })
        } else{

            viewModel.getTracks(previousArtist.toString())
            viewModel!!.tempSongList.observe(this, Observer{
                songList.clear()
                songList.addAll(it.data)
                trackAdapter.notifyDataSetChanged()
            })
        }

        FindSongs.setOnClickListener {
            if(search_box.text.toString() != ""){
                viewModel.getTracks(search_box.text.toString())

                viewModel!!.tempSongList.observe(this, Observer{
                    songList.clear()
                    songList.addAll(it.data)
                    trackAdapter.notifyDataSetChanged()
                })
            } else{
                (Toast.makeText(activity, "You may not leave this field empty", Toast.LENGTH_SHORT)).show()
            }
        }
    }


}
