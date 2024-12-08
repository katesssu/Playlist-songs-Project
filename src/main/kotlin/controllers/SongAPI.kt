package controllers

import models.Song

class SongAPI {

    private val songs = mutableListOf<Song>()
    private var lastId = 0
    private fun getId() = lastId++
    private fun isValidIndex(indexToArchive: Int): Boolean {
        TODO("Not yet implemented")
    }

    fun addSong(song: Song) {
        song.songId = getId()
        songs.add(song)
    }


    fun listSongs() = songs
}


