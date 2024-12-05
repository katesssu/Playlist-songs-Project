package models

class Song {
    data class Song(
        var songId: Int = 0,
        var songTitle: String = "",
        var artistName: String = "",
        var duration: String = "",
        var isFavorite: Boolean = false,
    )
}