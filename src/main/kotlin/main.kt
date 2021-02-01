import model.Game

/**
 * This method exists for ease of development and should not be used outside of testing purposes
 */
fun main() {
    val game = Game()
    game.startGui()

    val choice = game.textInput("Write a message and i will write it back")
    println(choice)
    game.showMessage(choice)
}