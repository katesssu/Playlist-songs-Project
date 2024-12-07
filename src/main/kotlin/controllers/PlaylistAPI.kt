package controllers

import models.Playlist

class PlaylistAPI {
    private val playlists = mutableListOf<Playlist>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addPlaylist(playlist: Playlist) {
        playlist.playlistId = getId()
        playlists.add(playlist)
    }

    fun listPlaylists() = playlists

}