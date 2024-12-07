package models

import java.sql.RowId

data class PlaylistSong(
    var songId: Int,
    var playlistId: Int
)