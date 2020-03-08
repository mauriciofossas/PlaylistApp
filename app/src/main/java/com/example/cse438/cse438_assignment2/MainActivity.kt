package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.TrackListAdapter
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Fragments.LikedSongsFragment
import com.example.cse438.cse438_assignment2.Fragments.PlaylistsFragment
import com.example.cse438.cse438_assignment2.Fragments.TracksFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.track_layout.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TrackViewModel

    var songList: ArrayList<Track> = ArrayList()

    var previousArtist : String? = ""

    var tabToStartAt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        previousArtist = intent?.getStringExtra("previous")

        tabToStartAt = intent.getIntExtra("tabToGoTo", 0)

        val fragment = TracksFragment()
        var bundle = Bundle()
        bundle.putString("previous", previousArtist)
        fragment.arguments = bundle

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        main_tabs.setupWithViewPager(viewpager_main)
        //Setup what tab the back button should send the users to.
        main_tabs.getTabAt(tabToStartAt)!!.select() //Way to open on different tab.
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() : Int {
            return 3
        }

        override fun getItem(position: Int) : Fragment {
            return when (position) {
                0 -> { TracksFragment() }
                1 -> { PlaylistsFragment() }
                else -> LikedSongsFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Tracks"
                1 -> "Playlists"
                else -> "Liked Songs"
            }
        }

    }

}
