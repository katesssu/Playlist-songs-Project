import java.lang.System.exit as exit

fun main(){
    runMenu()
}

fun mainMenu(): Int {
    print("""
          ----------------------------------
          |          PLAYLIST APP          |
          ----------------------------------
          | PLAYLIST MENU                  |
          |   1) Create playlist           |
          |   2) See existing playlists    |
          |   3) Add song to playlists     |
          |   4) Delete a playlist         |
          |   5) Favourite songs           |
          ----------------------------------
          |   0) Exit                      |
          ----------------------------------
          ==>> """)
    return readlnOrNull()?.toIntOrNull() ?: -1
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> createPlaylist()
            2  -> existPlaylist()
            3  -> addSong()
            4  -> deletePlaylist()
            5  -> favouriteSong()
            0  -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    } while (true)
}

fun createPlaylist(){
    println("You chose to Create a Playlist")
}

fun existPlaylist(){
    println("You chose to view an existing Playlist")
}

fun addSong(){
    println("You chose to Add a Song")
}

fun deletePlaylist(){
    println("You chose to delete a playlist")
}

fun favouriteSong(){
    println("You chose to favourite a Song")
}

fun exitApp(){
    println("Exiting the app, goodbye!")
    exit(/* status = */ 0)
}

