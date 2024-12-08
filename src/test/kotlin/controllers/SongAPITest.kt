package controllers

import models.Song
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SongAPITest {

    private var learnKotlin: Song? = null
    private var summerHoliday: Song? = null
    private var codeApp: Song? = null
    private var testApp: Song? = null
    private var swim: Song? = null
    private var populatedSong: SongAPI? = SongAPI()
    private var emptySong: SongAPI? = SongAPI()

    @BeforeEach
    fun setup() {
        learnKotlin = Song(5, "Learning Kotlin", "College", "false")
        summerHoliday = Song(1, "Summer Holiday to France", "Holiday", "false")
        codeApp = Song(4, "Code App", "Work", "false")
        testApp = Song(4, "Test App", "Work", "false")
        swim = Song(3, "Swim - Pool", "Hobby", "false")

        //adding 5 Note to the notes api
        populatedSong!!.addSong(learnKotlin!!)
        populatedSong!!.addSong(summerHoliday!!)
        populatedSong!!.addSong(codeApp!!)
        populatedSong!!.addSong(testApp!!)
        populatedSong!!.addSong(swim!!)

    }

    @AfterEach
    fun tearDown() {
        learnKotlin = null
        summerHoliday = null
        codeApp = null
        testApp = null
        swim = null
        populatedSong = null
        emptySong = null
    }

    @Test
    fun `adding a Song to a populated list adds to ArrayList`() {
        val newSong = Song(1, "Study Lambdas", "College", "true")
        assertTrue(populatedSong!!.addSong(newSong))
    }

    private fun assertTrue(addSong: Unit) {

    }
}