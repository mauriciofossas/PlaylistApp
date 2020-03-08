package com.example.cse438.cse438_assignment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Song::class), version = 3)
public abstract class SongRoomDatabase : RoomDatabase() {

    abstract fun songsDao(): SongsDao

    companion object {

        @Volatile
        private var INSTANCE: SongRoomDatabase? = null

        fun getDatabase(context: Context) : SongRoomDatabase {
            val temp = INSTANCE
            if(temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongRoomDatabase::class.java,
                    "song_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

    }

}