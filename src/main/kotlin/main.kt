import ui.Game

/**
 * This method exists for ease of development and should not be used outside of testing purposes
 */
fun main() {
    val game = Game()
    game.startGui()

    val choice = game.buttonInput("Choose a greeting","Hej", "Olloh")
    println(choice)
    game.showMessage("$choice, World!\n\nmulti line\n       string")
    Thread.sleep(2000)


    game.buttonInput("continue", "OK")
    game.eraseMessage()
}