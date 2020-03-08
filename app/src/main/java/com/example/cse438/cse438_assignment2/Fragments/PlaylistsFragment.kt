package com.example.cse438.cse438_assignment2.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.PlaylistListAdapter
import com.example.cse438.cse438_assignment2.AddPlaylistActivity
import com.example.cse438.cse438_assignment2.PlaylistViewModel

import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.SongsViewModel
import com.example.cse438.cse438_assignment2.database.Playlist
import kotlinx.android.synthetic.main.fragment_playlists.*

class PlaylistsFragment : Fragment() {

    private var playlistList: ArrayList<Playlist> = ArrayList()

    private lateinit var viewModel: PlaylistViewModel

    private lateinit var songVM: SongsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlists, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPlaylist_button.setOnClickListener {
            startActivity(Intent(this.context, AddPlaylistActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        var adapter = PlaylistListAdapter(playlistList)
        playlist_recycler_view.adapter = adapter
        playlist_recycler_view.layoutManager = LinearLayoutManager(this.context)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)

        songVM = ViewModelProvider(this).get(SongsViewModel::class.java)

        viewModel!!._playlistList.observe(this, Observer { playlists ->
            // Update the cached copy of the words in the adapter.
            playlistList.clear()
            playlistList.addAll(playlists)
            adapter.notifyDataSetChanged()
        })

        clearAllPlaylists_button.setOnClickListener {
            viewModel.clear()
            playlistList.clear()
            songVM.clearAll("false")
        }
    }


}