package com.example.cse438.cse438_assignment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Playlist::class), version = 2)
public abstract class PlaylistRoomDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDao

    companion object {

        @Volatile
        private var INSTANCE: PlaylistRoomDatabase? = null

        fun getDatabase(context: Context) : PlaylistRoomDatabase {
            val temp = INSTANCE
            if(temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistRoomDatabase::class.java,
                    "playlist_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}