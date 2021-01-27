import ui.Game

/**
 * This method exists for the ease of development should not be used outside of testing purposes
 */
suspend fun main() {
    val game = Game()
    game.startGui()

    println(
        game.buttonInput("Choose a greeting","Hej", "Olloh")
    )
}