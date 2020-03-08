package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cse438.cse438_assignment2.Fragments.TracksFragment
import com.example.cse438.cse438_assignment2.database.Song
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity() {

    lateinit var viewModel: TrackViewModel

    lateinit var songViewModel : SongsViewModel

    private var artist = ""
    private var title = ""
    private var duration = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
    }

    override fun onStart() {
        super.onStart()

        var id = intent!!.getStringExtra("id")

        viewModel = ViewModelProviders.of(this).get(TrackViewModel::class.java)

        viewModel.getFullTrack(id)

        viewModel.fullSong.observe(this, Observer {
            bigTitle.text = it.title
            if(it.title.length > 17){
                bigTitle.textSize = 28f
            }
            if(it.title.length > 27){
                bigTitle.textSize = 23f
            }
            title = it.title
            Picasso.get().load(it.album.cover).into(albumPicture)
            artistTxt.text = "Artist: " + it.artist.name
            artist = it.artist.name
            smallSong.text = it.title
            positionTxt.text = "Position: " + it.track_position
            length.text = "Duration: " + it.duration + " seconds"
            duration = it.duration.toString()
            releasedTxt.text = "Released: " + it.release_date
            rankTxt.text = "Rank: " + it.rank
            gainTxt.text = "Gain: " + it.gain
        })

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        moreLikeThis.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("previous", artist)
            startActivity(intent)
        }

        addToPlaylist.setOnClickListener {
            val intent = Intent(this, ShowPlaylistsToAddSongActivity::class.java)
            intent.putExtra("songToAdd", title)
            intent.putExtra("artistOfSongToAdd", artist)
            intent.putExtra("durationOfSongToAdd", duration)
            //To put so the user can come back to the song:
            intent.putExtra("idOfSong", id)
            startActivity(intent)
        }

        LikeThisSongButton.setOnClickListener{
            songViewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)
            val song = Song(title, artist, duration, "", "true")
            songViewModel.insert(song)
            (Toast.makeText(this, "Song has been added to your liked songs!", Toast.LENGTH_SHORT)).show()
        }

    }
}
