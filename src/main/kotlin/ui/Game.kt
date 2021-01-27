package ui

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import kotlin.properties.Delegates

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
                SetUpScreen(
                    messageArea = { Text("Hello, World!") },
                    inputArea = { Buttons("Hi", "Hello", "Hola") { println(it) } }
                )
            } else {
                GameScreen()
            }

        }
    }

    fun startGame() {
        hasStarted.value = true
    }

}





/**
 *
 */
@Composable
fun SetUpScreen(
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
fun GameScreen() {
    TODO("This Screen has not been implemented yet")
}