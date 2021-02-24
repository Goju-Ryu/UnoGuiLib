import model.Game
import model.Player
import ui.InputDropDownMenu

/**
 * This method exists for ease of development and should not be used outside of testing purposes
 */
fun main() {
    val game = Game()
    game.startGui()

    val playerCount = game.dropdownMenuInput(
        "Choose number of players",
        "1", "2", "3", "4", "5", "6"
    ).toInt()

    val names = mutableListOf<String>()
    for (i in 1..playerCount) {
        names.add(
            game.textInput("Enter your name:")
        )
    }

    game.players.addAll(names.map { Player(it) })
}