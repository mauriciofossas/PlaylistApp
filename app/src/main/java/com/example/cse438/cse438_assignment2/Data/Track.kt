package com.example.cse438.cse438_assignment2.Data

import java.util.*

data class Track (
    val id: String,
    //val readable: Boolean,
    val title: String,
    //val title_short: String,
    //val title_version: String,
    //val link: String,
    val duration: Integer,
    val rank: Integer,
    val explicit_lyrics: Boolean,
    //val explicit_content_lyrics: Integer,
    //val explicit_content_cover: Integer,
    //val preview: String,
    val artist: Artist,
    val album: Album
    //val type: String
)

data class FullTrack(
    val id: String,
    val title: String,
    val duration: Integer,
    val track_position: Integer,
    val rank: Integer,
    val release_date: String,
    val gain: String,
    val available_countries: List<String>,
    val artist: Artist,
    val album: Album
)

data class Payload(
    val data: List<Track>)

data class ChartPayload(
    val tracks: Payload
)