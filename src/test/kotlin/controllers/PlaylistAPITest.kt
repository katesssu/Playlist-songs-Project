package controllers

import models.Playlist
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PlaylistAPITest {

    private var playlistAPI: PlaylistAPI? = PlaylistAPI()
    private var emptyPlaylistAPI: PlaylistAPI? = PlaylistAPI()

    @BeforeEach
    fun setup() {
        // Add some test playlists
        playlistAPI!!.addPlaylist(Playlist(-1, "Rock Classics")) // ID will be assigned automatically
        playlistAPI!!.addPlaylist(Playlist(-1, "Chill Vibes"))
        playlistAPI!!.addPlaylist(Playlist(-1, "Workout Mix"))
    }

    @AfterEach
    fun tearDown() {
        playlistAPI = null
        emptyPlaylistAPI = null
    }

    @Test
    fun `adding a playlist increases count`() {
        val initialCount = playlistAPI!!.listPlaylists().size
        playlistAPI!!.addPlaylist(Playlist(-1, "New Playlist"))
        assertEquals(initialCount + 1, playlistAPI!!.listPlaylists().size)
    }

    @Test
    fun `adding playlist to empty collection increases count from 0 to 1`() {
        val initialCount = emptyPlaylistAPI!!.listPlaylists().size
        emptyPlaylistAPI!!.addPlaylist(Playlist(-1, "New Playlist"))
        val newCount = emptyPlaylistAPI!!.listPlaylists().size
        assertEquals(1, newCount)
    }

    @Test
    fun `listPlaylists returns all playlists`() {
        val playlists = playlistAPI!!.listPlaylists()
        assertEquals(3, playlists.size)
    }

    @Test
    fun `listPlaylists from empty collection returns empty list`() {
        val playlists = emptyPlaylistAPI!!.listPlaylists()
        assertTrue(playlists.isEmpty())
    }

    @Test
    fun `addPlaylist assigns incremental IDs`() {
        // Clear the existing playlists
        emptyPlaylistAPI = PlaylistAPI()

        emptyPlaylistAPI!!.addPlaylist(Playlist(-1, "Playlist 1"))
        emptyPlaylistAPI!!.addPlaylist(Playlist(-1, "Playlist 2"))
        emptyPlaylistAPI!!.addPlaylist(Playlist(-1, "Playlist 3"))

        val playlists = emptyPlaylistAPI!!.listPlaylists()
        assertEquals(0, playlists[0].playlistId)
        assertEquals(1, playlists[1].playlistId)
        assertEquals(2, playlists[2].playlistId)
    }

    @Test
    fun `addPlaylist overwrites existing ID with generated ID`() {
        emptyPlaylistAPI = PlaylistAPI()

        // Try to add a playlist with a specific ID
        val playlistWithId = Playlist(999, "Test Playlist")
        emptyPlaylistAPI!!.addPlaylist(playlistWithId)

        // The ID should be overwritten with the generated ID
        val addedPlaylist = emptyPlaylistAPI!!.listPlaylists().first()
        assertNotEquals(999, addedPlaylist.playlistId)
        assertEquals(0, addedPlaylist.playlistId) // First ID should be 0
    }

    @Test
    fun `playlist names are preserved when added`() {
        val playlists = playlistAPI!!.listPlaylists()
        val playlistNames = playlists.map { it.playlistName }

        assertTrue(playlistNames.contains("Rock Classics"))
        assertTrue(playlistNames.contains("Chill Vibes"))
        assertTrue(playlistNames.contains("Workout Mix"))
    }

    @Test
    fun `multiple playlists can have the same name`() {
        emptyPlaylistAPI!!.addPlaylist(Playlist(-1, "Duplicate Name"))
        emptyPlaylistAPI!!.addPlaylist(Playlist(-1, "Duplicate Name"))

        val playlists = emptyPlaylistAPI!!.listPlaylists()
        assertEquals(2, playlists.size)
        assertEquals("Duplicate Name", playlists[0].playlistName)
        assertEquals("Duplicate Name", playlists[1].playlistName)
    }
}