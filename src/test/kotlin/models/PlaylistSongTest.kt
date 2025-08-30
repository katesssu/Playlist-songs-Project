package models

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class PlaylistSongTest {

    private lateinit var playlistSong: PlaylistSong

    @BeforeEach
    fun setUp() {
        // Initialize a PlaylistSong object before each test
        playlistSong = PlaylistSong(1, 2) // songId=1, playlistId=2
    }

    @Test
    fun `playlistSong properties are correctly set`() {
        assertEquals(1, playlistSong.songId)
        assertEquals(2, playlistSong.playlistId)
    }

    @Test
    fun `playlistSong equality works correctly`() {
        val samePlaylistSong = PlaylistSong(1, 2)
        val differentSongId = PlaylistSong(3, 2)
        val differentPlaylistId = PlaylistSong(1, 4)
        val completelyDifferent = PlaylistSong(5, 6)

        assertEquals(playlistSong, samePlaylistSong)
        assertNotEquals(playlistSong, differentSongId)
        assertNotEquals(playlistSong, differentPlaylistId)
        assertNotEquals(playlistSong, completelyDifferent)
    }

    @Test
    fun `playlistSong hashCode is consistent with equals`() {
        val samePlaylistSong = PlaylistSong(1, 2)

        assertEquals(playlistSong.hashCode(), samePlaylistSong.hashCode())
    }

    @Test
    fun `playlistSong toString contains expected information`() {
        val toString = playlistSong.toString()

        assertTrue(toString.contains("songId=1"))
        assertTrue(toString.contains("playlistId=2"))
    }

    @Test
    fun `playlistSong copy method works correctly`() {
        val copy = playlistSong.copy(songId = 3, playlistId = 4)

        assertEquals(3, copy.songId)
        assertEquals(4, copy.playlistId)

        // Original playlistSong should remain unchanged
        assertEquals(1, playlistSong.songId)
        assertEquals(2, playlistSong.playlistId)
    }

    @Test
    fun `playlistSong can update properties`() {
        playlistSong.songId = 5
        playlistSong.playlistId = 6

        assertEquals(5, playlistSong.songId)
        assertEquals(6, playlistSong.playlistId)
    }

    @Test
    fun `playlistSong component functions work correctly`() {
        val (songId, playlistId) = playlistSong

        assertEquals(1, songId)
        assertEquals(2, playlistId)
    }

    @Test
    fun `playlistSong with same songId and playlistId should be equal`() {
        val playlistSong1 = PlaylistSong(1, 2)
        val playlistSong2 = PlaylistSong(1, 2)

        assertEquals(playlistSong1, playlistSong2)
    }

    @Test
    fun `playlistSong with different songId should not be equal`() {
        val playlistSong1 = PlaylistSong(1, 2)
        val playlistSong2 = PlaylistSong(3, 2)

        assertNotEquals(playlistSong1, playlistSong2)
    }

    @Test
    fun `playlistSong with different playlistId should not be equal`() {
        val playlistSong1 = PlaylistSong(1, 2)
        val playlistSong2 = PlaylistSong(1, 4)

        assertNotEquals(playlistSong1, playlistSong2)
    }
}