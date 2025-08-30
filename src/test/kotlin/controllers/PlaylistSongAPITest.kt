package controllers

import models.PlaylistSong
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PlaylistSongAPITest {

    private var playlistSongAPI: PlaylistSongAPI? = PlaylistSongAPI()
    private var emptyPlaylistSongAPI: PlaylistSongAPI? = PlaylistSongAPI()

    @BeforeEach
    fun setup() {
        // Add some test playlist-song associations
        playlistSongAPI!!.addSongToPlaylist(1, 1) // songId=1, playlistId=1
        playlistSongAPI!!.addSongToPlaylist(2, 1) // songId=2, playlistId=1
        playlistSongAPI!!.addSongToPlaylist(3, 2) // songId=3, playlistId=2
        playlistSongAPI!!.addSongToPlaylist(1, 3) // songId=1, playlistId=3
    }

    @AfterEach
    fun tearDown() {
        playlistSongAPI = null
        emptyPlaylistSongAPI = null
    }

    @Test
    fun `adding a song to playlist increases association count`() {
        val initialCount = playlistSongAPI!!.listSongsInPlaylist(1).size
        playlistSongAPI!!.addSongToPlaylist(4, 1) // Add songId=4 to playlistId=1
        assertEquals(initialCount + 1, playlistSongAPI!!.listSongsInPlaylist(1).size)
    }

    @Test
    fun `listSongsInPlaylist returns correct songs for existing playlist`() {
        val songs = playlistSongAPI!!.listSongsInPlaylist(1)
        assertEquals(2, songs.size) // Should find 2 songs for playlistId=1

        // Verify the song IDs are correct
        val songIds = songs.map { it.songId }
        assertTrue(songIds.contains(1))
        assertTrue(songIds.contains(2))
    }

    @Test
    fun `listSongsInPlaylist returns empty list for non-existent playlist`() {
        val songs = playlistSongAPI!!.listSongsInPlaylist(999)
        assertTrue(songs.isEmpty())
    }

    @Test
    fun `listSongsInPlaylist from empty collection returns empty list`() {
        val songs = emptyPlaylistSongAPI!!.listSongsInPlaylist(1)
        assertTrue(songs.isEmpty())
    }

    @Test
    fun `can add same song to different playlists`() {
        // Song ID 1 is already in playlists 1 and 3
        val playlist1Songs = playlistSongAPI!!.listSongsInPlaylist(1)
        val playlist3Songs = playlistSongAPI!!.listSongsInPlaylist(3)

        // Both playlists should contain song ID 1
        assertTrue(playlist1Songs.any { it.songId == 1 })
        assertTrue(playlist3Songs.any { it.songId == 1 })
    }

    @Test
    fun `can add different songs to same playlist`() {
        // Playlist ID 1 already has songs 1 and 2
        val playlistSongs = playlistSongAPI!!.listSongsInPlaylist(1)

        // Should contain both song IDs
        assertTrue(playlistSongs.any { it.songId == 1 })
        assertTrue(playlistSongs.any { it.songId == 2 })
    }

    @Test
    fun `playlistSong associations preserve songId and playlistId`() {
        val association = playlistSongAPI!!.listSongsInPlaylist(1).first()
        assertEquals(1, association.songId)
        assertEquals(1, association.playlistId)
    }

    @Test
    fun `adding duplicate song-playlist association creates duplicate entry`() {
        val initialCount = playlistSongAPI!!.listSongsInPlaylist(1).size
        playlistSongAPI!!.addSongToPlaylist(1, 1) // Add duplicate association
        assertEquals(initialCount + 1, playlistSongAPI!!.listSongsInPlaylist(1).size)
    }

    @Test
    fun `can retrieve all associations for a playlist with multiple songs`() {
        // Add more songs to playlist 1
        playlistSongAPI!!.addSongToPlaylist(4, 1)
        playlistSongAPI!!.addSongToPlaylist(5, 1)

        val songs = playlistSongAPI!!.listSongsInPlaylist(1)
        assertEquals(4, songs.size) // Should have 4 songs now
    }
}