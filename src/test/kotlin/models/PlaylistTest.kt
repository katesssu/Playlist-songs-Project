package models

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class PlaylistTest {

    private lateinit var playlist: Playlist

    @BeforeEach
    fun setUp() {
        // Initialize a playlist object before each test
        playlist = Playlist(1, "My Favorites")
    }

    @Test
    fun `playlist properties are correctly set`() {
        assertEquals(1, playlist.playlistId)
        assertEquals("My Favorites", playlist.playlistName)
    }

    @Test
    fun `playlist equality works correctly`() {
        val samePlaylist = Playlist(1, "My Favorites")
        val differentId = Playlist(2, "My Favorites")
        val differentName = Playlist(1, "Rock Classics")
        val completelyDifferent = Playlist(2, "Rock Classics")

        assertEquals(playlist, samePlaylist)
        assertNotEquals(playlist, differentId)
        assertNotEquals(playlist, differentName)
        assertNotEquals(playlist, completelyDifferent)
    }

    @Test
    fun `playlist hashCode is consistent with equals`() {
        val samePlaylist = Playlist(1, "My Favorites")

        assertEquals(playlist.hashCode(), samePlaylist.hashCode())
    }

    @Test
    fun `playlist toString contains expected information`() {
        val toString = playlist.toString()

        assertTrue(toString.contains("My Favorites"))
        assertTrue(toString.contains("1"))
    }

    @Test
    fun `playlist copy method works correctly`() {
        val copy = playlist.copy(playlistName = "Rock Classics")

        assertEquals(1, copy.playlistId)
        assertEquals("Rock Classics", copy.playlistName)

        // Original playlist should remain unchanged
        assertEquals("My Favorites", playlist.playlistName)
    }

    @Test
    fun `playlist can update properties`() {
        playlist.playlistId = 2
        playlist.playlistName = "Updated Playlist"

        assertEquals(2, playlist.playlistId)
        assertEquals("Updated Playlist", playlist.playlistName)
    }

    @Test
    fun `playlist component functions work correctly`() {
        val (id, name) = playlist

        assertEquals(1, id)
        assertEquals("My Favorites", name)
    }
}