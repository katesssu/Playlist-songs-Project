import utils.readIntNotNull
import utils.readNextInt
import java.lang.System.exit as exit
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

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
          ==>> """.trimMargin(">"))
    return readNextInt(" > ==>>")
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
    logger.info{"createPlaylist() function invoked"}
}

fun existPlaylist(){
    logger.info{"existPlaylist() function invoked"}
}

fun addSong(){
    logger.info{"addSong() function invoked"}
}

fun deletePlaylist(){
    logger.info{"deletePlaylist() function invoked"}
}

fun favouriteSong(){
    logger.info{"favouriteSong() function invoked"}
}

fun exitApp(){
    logger.info{"existApp() function invoked"}
    exit(/* status = */ 0)
}

