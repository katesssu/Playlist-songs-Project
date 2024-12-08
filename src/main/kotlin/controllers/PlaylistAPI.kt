package controllers

import models.Playlist

/**
 * PlaylistAPI provides functionality for managing playlists.
 * It allows adding playlists and listing all stored playlists.
 */
class PlaylistAPI {

    // List to hold all playlists
    private val playlists = mutableListOf<Playlist>()

    // Keeps track of the last assigned playlist ID
    private var lastId = 0

    /**
     * Generates a unique ID for a new playlist.
     * The ID is incremented each time it's called.
     *
     * @return The next available playlist ID.
     */
    private fun getId() = lastId++

    /**
     * Adds a new playlist to the list of playlists.
     *
     * @param playlist The playlist to add.
     */
    fun addPlaylist(playlist: Playlist) {
        playlist.playlistId = getId()  // Assign a unique ID to the playlist
        playlists.add(playlist)        // Add the playlist to the list
    }

    /**
     * Returns the list of all playlists.
     *
     * @return A list of playlists.
     */
    fun listPlaylists() = playlists
}
