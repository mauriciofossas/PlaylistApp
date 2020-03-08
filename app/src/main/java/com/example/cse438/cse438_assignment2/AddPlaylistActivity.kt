package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.database.Playlist
import kotlinx.android.synthetic.main.activity_add_playlist.*

class AddPlaylistActivity : AppCompatActivity() {

    private var playlistViewModel: PlaylistViewModel? = null

    var playListName = ""
    var playListRating = ""
    var playListDescription = ""
    var playListGenre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_playlist)

        addPlaylistToDatabase.setOnClickListener {

            playListName = Playlist_name.text.toString()
            playListRating = Playlist_rating.text.toString()
            playListDescription = Playlist_description.text.toString()
            playListGenre = Playlist_genre.text.toString()

            //Input sanitation
            if(playListName != " " && playListDescription != " " && playListGenre != " " && isDoubleLessThan10(playListRating)){
                playlistViewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)

                val pl = Playlist(playListName, playListDescription, playListRating.toDouble(), playListGenre)

                playlistViewModel!!.insert(pl)

                (Toast.makeText(this, "Playlist Added!", Toast.LENGTH_SHORT)).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("tabToGoTo", 1)

                startActivity(intent)
            } else{
                (Toast.makeText(this, "Please fill in all fields appropriately", Toast.LENGTH_SHORT)).show()
            }


        }
    }

    fun isDoubleLessThan10(number : String) : Boolean{
        try{
            val converted = number.toDouble()
            return converted in 0.0..10.0
        } catch (e : Exception){
            return false
        }
    }
}
