package ui

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AmbientContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.properties.Delegates


/**
 * This class holds the state of the game as well as methods for interacting with the UI.
 * It is the main interface through which you can interact with the gui and players.
 */
class Game {

    /**
     * This state represents weather or not the game is in setup mode or has started.
     */
    private val hasStartedState = mutableStateOf(false)

    /**
     * This String is the message that will be displayed on the board
     */
    private val messageState = mutableStateOf("Hello")

    /**
     * This State contains the composable representing the currently visible inputmethod
     */
    private val inputState: MutableState<(@Composable () -> Unit)?> = mutableStateOf({
        Buttons("Hi", "Hello", "Hola", "Start Game") {
            messageState.value = it; if (it == "Start Game") startGame()
        }
    })

    /**
     * This function starts the gui window and allows the game to begin
     */
    fun startGui() = Window(
        title = "My Uno Game",
        size = IntSize(1280, 1024)
    ) {

        val hasStarted by remember { hasStartedState }

        MaterialTheme {
            if (!hasStarted) {
                val message by remember { messageState }
                val inputArea by remember { inputState }

                SetUpScreen(
                    messageArea = { Text("$message, World!") },
                    inputArea = inputArea
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
        hasStartedState.value = true
    }

    /**
     * Prompt the user to make a choice by clicking one of an arbitrary number off buttons
     *
     * @param message a string to be shown to the user, this should explain what kind of choice is made
     * @param buttonNames an number of strings defining the text of each button
     * @return the name of the button that was clicked
     */
    fun buttonInput(message: String, vararg buttonNames: String): String {
        messageState.value = message
        val choice: String by lazy {
            var temp: String? = null
            inputState.value = {
                Buttons(*buttonNames) {
                    inputState.value = null
                    temp = it
                }
            }
            while (temp == null) Thread.sleep(100)
            temp!!
        }

        return choice
    }
}


/**
 * This screen is meant for setting up the game.
 * In this screen it is easy to ask for number of players, names and more.
 */
@Composable
internal fun SetUpScreen(
    messageArea: (@Composable () -> Unit),
    inputArea: (@Composable () -> Unit)? = null
) {
    Column {
        messageArea()
        inputArea.orDefault {
            Box(Modifier.fillMaxSize()) {
                Surface(
                    Modifier.fillMaxSize(),
                    color = AmbientContentColor.current.copy(red = 0.2f)
                ) {}
            }
        }()
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


internal fun <T> T?.orDefault(default: T): T = this ?: default