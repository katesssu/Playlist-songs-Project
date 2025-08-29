package models

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SongTest {

    @Test
    fun `song properties are correctly set`() {
        val song = Song(1, "Bohemian Rhapsody", "Queen", "5:55")

        assertEquals(1, song.songId)
        assertEquals("Bohemian Rhapsody", song.songName)
        assertEquals("Queen", song.artist)
        assertEquals("5:55", song.songDuration)
    }

    @Test
    fun `song equality works correctly`() {
        val song1 = Song(1, "Bohemian Rhapsody", "Queen", "5:55")
        val song2 = Song(1, "Bohemian Rhapsody", "Queen", "5:55")
        val song3 = Song(2, "We Will Rock You", "Queen", "2:03")

        assertEquals(song1, song2)
        assertNotEquals(song1, song3)
    }

    @Test
    fun `song toString contains expected information`() {
        val song = Song(1, "Bohemian Rhapsody", "Queen", "5:55")
        val toString = song.toString()

        assertTrue(toString.contains("Bohemian Rhapsody"))
        assertTrue(toString.contains("Queen"))
        assertTrue(toString.contains("5:55"))
    }

    @Test
    fun `song copy method works correctly`() {
        val original = Song(1, "Bohemian Rhapsody", "Queen", "5:55")
        val copy = original.copy(songName = "We Will Rock You")

        assertEquals(1, copy.songId)
        assertEquals("We Will Rock You", copy.songName)
        assertEquals("Queen", copy.artist)
        assertEquals("5:55", copy.songDuration)
    }
}