package ui

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp


/**
 * This class holds the state of the game as well as methods for interacting with the UI.
 * It is the main interface through which you can interact with the gui and players.
 */
class Game {

    /**
     * This state represents weather or not the game is in setup mode or has started.
     */
    private val hasStarted = mutableStateOf(false)


    /**
     * This function starts the gui window and allows the game to begin
     */
    fun startGui() = Window(
        title = "My Uno Game",
        size = IntSize(1280, 1024)
    ) {

        remember { hasStarted }

        MaterialTheme {
            if (!hasStarted.value) {
                var message by remember { mutableStateOf("Hello") }
                SetUpScreen(
                    messageArea = { Text("$message, World!") },
                    inputArea = {
                        Buttons("Hi", "Hello", "Hola", "Start Game") {
                            message = it; if (it == "Start Game") startGame()
                        }
                    }
                )
            } else {
                GameScreen()
            }

        }
    }

    /**
     * This function starts the game. You should get number of players as well as their names before
     * invoking this function. It cannot be undone once called.
     */
    fun startGame() {
        hasStarted.value = true
    }

}





/**
 * This screen is meant for setting up the game.
 * In this screen it is easy to ask for number of players, names and more.
 */
@Composable
internal fun SetUpScreen(
    messageArea: @Composable () -> Unit = { Box(Modifier.fillMaxSize()) },
    inputArea: @Composable () -> Unit = { Box(Modifier.fillMaxSize()) }
) {
    Column {
        messageArea()
        inputArea()
    }
}


/**
 * This screen contains the UI for when a game has started
 */
@Composable
internal fun GameScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("This Screen Has Not been implemented yet", fontSize = 42.sp)
    }
}