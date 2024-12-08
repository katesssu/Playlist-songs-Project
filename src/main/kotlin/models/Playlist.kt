package models

/**
 * Represents a playlist with an ID and a name.
 *
 * @property playlistId The unique identifier for the playlist.
 * @property playlistName The name of the playlist.
 */
data class Playlist(
    var playlistId: Int,      // Unique identifier for the playlist
    var playlistName: String  // Name of the playlist
)
