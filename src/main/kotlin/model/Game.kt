package model

import androidx.compose.desktop.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntSize
import ui.*
import ui.InputButtons
import ui.InputEditText
import ui.GameScreen
import ui.OnInputConfirmed
import ui.SetUpScreen

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
    private val messageState: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)

    /**
     * This State contains the composable representing the currently visible inputmethod
     */
    private val inputState: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)

    /**
     * This function starts the gui window and allows the game to begin
     */
    fun startGui() = Window(
        title = "My Uno Game",
        size = IntSize(1280, 720),
    ) {
        val hasStarted by remember { hasStartedState }
        val messageArea by remember { messageState }
        val inputArea by remember { inputState }

        MaterialTheme {
            if (!hasStarted) {
                SetUpScreen(
                    messageArea = messageArea,
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
        showMessage(message)
        return getInput { onClick ->
            InputButtons(
                { name -> onClick(name) },
                *buttonNames
            )
        }
    }

    /**
     * This method makes a text field in which the user can write any text they like
     *
     * @param message a string to be shown to the user, this should explain what kind of choice is made
     * @return The text written by the user.
     */
    fun textInput(message: String): String {
        showMessage(message)
        return getInput { onAccept ->
            InputEditText({ text ->
                onAccept(text)
            })
        }
    }

    fun dropdownMenuInput(message: String, vararg options: String): String {
        showMessage(message)
        return getInput { onAccept ->
            InputDropDownMenu(
                { option -> onAccept(option) },
                *options
            )
        }
    }

    fun checkboxInput(message: String, vararg options: String): List<String> {
        showMessage(message)
        return getInputList { onAccept ->
            InputCheckBox(
                { option -> onAccept(option) },
                *options
            )
        }
    }

    /**
     * Shows a message to the user
     * @param message the message to show
     */
    fun showMessage(message: String) {
        messageState.value = { Text(message) }
    }

    /**
     * Clears the message space
     */
    fun eraseMessage() {
        messageState.value = null
    }

    /**
     * This function takes a UI component and applies the logic for waiting for input to it.
     * In this way it can take an arbitrary composable and make it into a public input api.
     *
     * @see buttonInput
     * @see textInput
     */
    private fun getInput(uiComponent: (@Composable (OnInputConfirmed) -> Unit)): String {
        val choice: String by lazy {
            var temp: String? = null
            inputState.value = {
                uiComponent {
                    inputState.value = null
                    temp = it
                }
            }
            while (temp == null) Thread.sleep(100)
            temp!!
        }
        return choice
    }


    /**
     * This function takes a UI component and applies the logic for waiting for input to it.
     * In this way it can take an arbitrary composable and make it into a public input api.
     *
     * @see buttonInput
     * @see textInput
     */
    private fun getInputList(uiComponent: (@Composable ((List<String>) -> Unit) -> Unit)): List<String> {
        val choice: List<String> by lazy {
            var temp: List<String>? = null
            inputState.value = {
                uiComponent {
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