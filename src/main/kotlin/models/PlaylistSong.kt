package models

/**
 * Represents the association between a song and a playlist.
 *
 * @property songId The unique identifier for the song.
 * @property playlistId The unique identifier for the playlist.
 */
data class PlaylistSong(
    var songId: Int,      // Unique identifier for the song
    var playlistId: Int   // Unique identifier for the playlist
)
