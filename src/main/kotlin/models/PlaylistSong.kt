package models

class PlaylistSong {
    data class PlaylistSong(
                            var playlistId: Int,
                            var songId: Int,
                            var order: Int = 0,
    )
}