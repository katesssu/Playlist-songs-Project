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


}
