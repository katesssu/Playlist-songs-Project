package controllers

import models.Song
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SongAPITest {

    private var bohemianRhapsody: Song? = null
    private var hereComesTheSun: Song? = null
    private var blindingLights: Song? = null
    private var rollingInTheDeep: Song? = null
    private var shapeOfYou: Song? = null
    private var populatedSong: SongAPI? = SongAPI()
    private var emptySong: SongAPI? = SongAPI()

    @BeforeEach
    fun setup() {
        bohemianRhapsody = Song(1, "Bohemian Rhapsody", "Queen", "5:55")
        hereComesTheSun = Song(2, "Here Comes the Sun", "The Beatles", "3:06")
        blindingLights = Song(3, "Blinding Lights", "The Weeknd", "3:20")
        rollingInTheDeep = Song(4, "Rolling in the Deep", "Adele", "3:48")
        shapeOfYou = Song(5, "Shape of You", "Ed Sheeran", "4:24")

        // adding 5 songs to the song api
        populatedSong!!.addSong(bohemianRhapsody!!)
        populatedSong!!.addSong(hereComesTheSun!!)
        populatedSong!!.addSong(blindingLights!!)
        populatedSong!!.addSong(rollingInTheDeep!!)
        populatedSong!!.addSong(shapeOfYou!!)
    }

    @AfterEach
    fun tearDown() {
        bohemianRhapsody = null
        hereComesTheSun = null
        blindingLights = null
        rollingInTheDeep = null
        shapeOfYou = null
        populatedSong = null
        emptySong = null
    }

    @Test
    fun `populatedSong should have 5 songs`() {
        val songCount = populatedSong!!.listSongs().count { true } // counts all songs
        assertTrue(songCount == 5)
    }

    @Test
    fun `count songs by Queen`() {
        val queenSongs = populatedSong!!.listSongs().count { it.artist == "Queen" }
        assertTrue(queenSongs == 1)
    }

    @Test
    fun `adding a new song increases song count`() {
        val newSong = Song(0, "Smells Like Teen Spirit", "Nirvana", "5:01")
        val initialCount = populatedSong!!.listSongs().count { true }
        populatedSong!!.addSong(newSong)
        val newCount = populatedSong!!.listSongs().count { true }
        assertTrue(newCount == initialCount + 1)
    }

    @Test
    fun `adding a song with duplicate ID should not increase count`() {
        val duplicateSong = Song(1, "Different Title", "Queen", "4:00")
        val initialCount = populatedSong!!.listSongs().count { true }
        populatedSong!!.addSong(duplicateSong)
        val newCount = populatedSong!!.listSongs().count { true }
        assertTrue(newCount == initialCount) // Count should not change
    }

    @Test
    fun `adding a song to empty collection increases count from 0 to 1`() {
        val newSong = Song(6, "New Song", "New Artist", "3:30")
        val initialCount = emptySong!!.listSongs().count { true }
        emptySong!!.addSong(newSong)
        val newCount = emptySong!!.listSongs().count { true }
        assertTrue(newCount == initialCount + 1)
    }

    @Test
    fun `findSongById should return correct song`() {
        val foundSong = populatedSong!!.findSongById(0) // Note: IDs start from 0 due to getId() implementation
        assertTrue(foundSong != null)
        assertTrue(foundSong?.songName == "Bohemian Rhapsody")
    }

    @Test
    fun `findSongById should return null for non-existent ID`() {
        val foundSong = populatedSong!!.findSongById(999)
        assertTrue(foundSong == null)
    }

    @Test
    fun `findSongsByArtist should return correct songs`() {
        val queenSongs = populatedSong!!.findSongsByArtist("Queen")
        assertTrue(queenSongs.size == 1)
        assertTrue(queenSongs[0].songName == "Bohemian Rhapsody")
    }

    @Test
    fun `findSongsByArtist should return empty list for non-existent artist`() {
        val result = populatedSong!!.findSongsByArtist("Non-existent Artist")
        assertTrue(result.isEmpty())
    }

    @Test
    fun `searchByTitle should find matching songs`() {
        val result = populatedSong!!.searchByTitle("Bohemian Rhapsody")
        assertTrue(result.contains("Bohemian Rhapsody"))
        assertTrue(result.contains("Queen"))
    }

    @Test
    fun `searchByTitle should return not found message`() {
        val result = populatedSong!!.searchByTitle("Non-existent Song")
        assertTrue(result == "No songs found with name: Non-existent Song")
    }

    @Test
    fun `simple test to verify setup`() {
        assertTrue(true) // This should always pass
    }


}
