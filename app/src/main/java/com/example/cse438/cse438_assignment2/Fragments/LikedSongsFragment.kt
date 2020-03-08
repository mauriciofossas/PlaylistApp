package com.example.cse438.cse438_assignment2.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.SongAdapter
import com.example.cse438.cse438_assignment2.Adapter.TrackListAdapter

import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.SongsViewModel
import com.example.cse438.cse438_assignment2.TrackViewModel
import com.example.cse438.cse438_assignment2.database.Song
import kotlinx.android.synthetic.main.fragment_liked_songs.*

class LikedSongsFragment : Fragment() {

    lateinit var viewModel: SongsViewModel

    var likedsongList: ArrayList<Song> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liked_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)

        viewModel.getLikedSongs("true")

        var adapter = SongAdapter(likedsongList, viewModel)
        likedSongsRecyclerView.adapter = adapter
        likedSongsRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel!!._likedSongList?.observe(this, Observer {
            likedsongList.clear()
            likedsongList.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }

}
