# PlaylistApp
Playlist app; on startup the app displays the top chart songs, taken from the deezer API. Users can filter by song or artist
and they can add songs to their liked songs or any number of created playlists. Playlists contain a description, rating, 
title, and genre. There is data persistance for the playlists, songs in the playlists, and liked songs through the use of
databases which are instantiated using androids room library and songs are retrireved from the deezer API 
(https://developers.deezer.com/api) using the retrofit and moshi libraries.

Main goal of this project is to create better looking apps (using recyclerviews and gridviews), and managing data with
databases and api calls through the use of android libraries. 
