package models

/**
 * Represents a song with its ID, name, artist, and duration.
 *
 * @property songId The unique identifier for the song.
 * @property songName The name of the song.
 * @property artist The artist who performed the song.
 * @property songDuration The duration of the song in string format (e.g., "3:45").
 */
data class Song(
    var songId: Int,       // Unique identifier for the song
    var songName: String,  // Name of the song
    var artist: String,    // Artist who performed the song
    var songDuration: String, // Duration of the song (e.g., "3:45")
)
