package controllers

import models.PlaylistSong

/**
 * PlaylistSongAPI provides functionality for managing songs in playlists.
 * It allows adding songs to playlists and listing songs in a specific playlist.
 */
class PlaylistSongAPI {

    // List to hold all PlaylistSong associations
    private val playlistSongs = mutableListOf<PlaylistSong>()

    /**
     * Adds a song to a playlist by associating the song's ID with the playlist's ID.
     *
     * @param songId The ID of the song to add.
     * @param playlistId The ID of the playlist to which the song will be added.
     */
    fun addSongToPlaylist(songId: Int, playlistId: Int): Boolean {
        if (playlistSongs.any { it.songId == songId && it.playlistId == playlistId }) {
            return false
        }
        playlistSongs.add(PlaylistSong(songId, playlistId))
        return true
    }

    /**
     * Retrieves all songs in a specific playlist.
     *
     * @param playlistId The ID of the playlist to retrieve songs for.
     * @return A list of PlaylistSong objects that belong to the given playlist.
     */
    fun listSongsInPlaylist(playlistId: Int) = playlistSongs.filter { it.playlistId == playlistId }
}
