package controllers

import models.PlaylistSong

class PlaylistSongAPI {

    private val playlistSongs = mutableListOf<PlaylistSong>()

    fun addSongToPlaylist(songId: Int, playlistId: Int) {
        playlistSongs.add(PlaylistSong(songId, playlistId))
    }

    fun listSongsInPlaylist(playlistId: Int) = playlistSongs.filter { it.playlistId == playlistId }

}