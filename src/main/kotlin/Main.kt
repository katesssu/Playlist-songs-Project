import controllers.PlaylistAPI
import controllers.PlaylistSongAPI
import controllers.SongAPI
import models.Playlist
import models.Song
import utils.readNextInt
import utils.readNextLine

fun main() {
    val playlistAPI = PlaylistAPI()
    val songAPI = SongAPI()
    val playlistSongAPI = PlaylistSongAPI()

    var choice: Int

    do {
        choice = readNextInt("""
            
                      >----------------------------------
                      >|          Playlist App          |
                      >----------------------------------             
                      >|  CREATE MENU                   |
                      >|  1. Add Song                   |
                      >|  2. Create Playlist            |
                      >----------------------------------
                      >|  ADD TO PLAYLIST               |
                      >|  3. Add Song to Playlist       |
                      >----------------------------------
                      >|   VIEW MENU                    |
                      >|  4. View Songs                 |
                      >|  5. View Playlists             |
                      >|  6. List Songs in Playlists    |
                      >----------------------------------
                      >|  0. Exit                       |
                      >----------------------------------
                      >|  > """.trimMargin(">"))

        when (choice) {
            1 -> {
                // Add Song
                val songName = readNextLine("Song Name: ")
                val artist = readNextLine("Artist: ")
                val duration =  readNextLine("Duration: ")
                val song = Song(0, songName, artist, duration)
                songAPI.addSong(song)
            }
            2 -> {
                // Add Playlist
                val playlistName = readNextLine("Playlist Name: ")
                val playlist = Playlist(0, playlistName)
                playlistAPI.addPlaylist(playlist)
            }
            3 -> {
                // Add Song to Playlist
                val songId = readNextInt("Enter song id: ")
                val playlistId = readNextInt("Enter playlist id: ")
                playlistSongAPI.addSongToPlaylist(songId, playlistId)
            }
            4 -> {
                // View Songs
                println("Songs: \n${songAPI.listSongs()}")
            }
            5 -> {
                // View Playlists
                println(" Playlists: \n${playlistAPI.listPlaylists()}")
            }
            6 -> {
                // List Songs in Playlists
                val playlistId = readNextInt("Enter playlist id: ")
                println(playlistSongAPI.listSongsInPlaylist(playlistId))
            }

            0 -> {
                println("Exiting...")
            }
            else -> println("Invalid choice: $choice. Please try again.")
        }
    } while (choice != 0)
}