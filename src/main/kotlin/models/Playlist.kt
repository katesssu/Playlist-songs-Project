package models

import java.security.PublicKey

class Playlist {
    data class Playlist(var playlistId: Int = 0,
                        var playlistTitle: String,
                        var playlistLength: String,
                        var isPublic: Boolean = true,
                        var numberOfSongs: Int = 0,
                        var dateCreated: String,

        )
}