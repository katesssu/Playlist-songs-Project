package controllers

import models.Song

/**
 * SongAPI provides functionality for managing songs.
 * It allows adding songs and listing all stored songs.
 */
class SongAPI {

    // List to hold all songs
    private val songs = mutableListOf<Song>()

    // Keeps track of the last assigned song ID
    private var lastId = 0

    /**
     * Generates a unique ID for a new song.
     * The ID is incremented each time it's called.
     */
    private fun getId() = lastId++

    /**
     * Checks if the given index is valid (this function is not yet implemented).
     *
     * @param indexToArchive The index to check for validity.
     * @return Boolean indicating whether the index is valid.
     */
    private fun isValidIndex(indexToArchive: Int): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * Adds a new song to the list of songs.
     *
     * @param song The song to add.
     */
    fun addSong(song: Song) {
        song.songId = getId()  // Assign a unique ID to the song
        songs.add(song)        // Add the song to the list
    }

    /**
     * Returns the list of all songs.
     *
     * @return List of songs.
     */
    fun listSongs() = songs

    /**
     * Searches for songs that match the given name.
     *
     * @param name The song name to search for.
     * @return A formatted string with matching songs (index and details), or a message if none are found.
     */
    fun searchByTitle(name: String): String {
        return songs
            .withIndex()
            .filter { it.value.songName.equals(name, ignoreCase = true) }
            .joinToString(separator = "\n") { "${it.index}: ${it.value}" }
            .ifEmpty { "No songs found with name: $name" }
    }

    fun findSongById(id: Int): Song? {
        return songs.find { it.songId == id }
    }

    fun findSongsByArtist(artist: String): List<Song> {
        return songs.filter { it.artist.equals(artist, ignoreCase = true) }
    }




}
