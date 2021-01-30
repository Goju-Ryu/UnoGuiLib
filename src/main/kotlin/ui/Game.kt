package ui

import androidx.compose.desktop.Window
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp





/**
 * This screen is meant for setting up the game.
 * In this screen it is easy to ask for number of players, names and more.
 */
@Composable
internal fun SetUpScreen(
    messageArea: (@Composable () -> Unit)? = null,
    inputArea: (@Composable () -> Unit)? = null
) {
    val mod = Modifier
        .border(2.dp, AmbientContentColor.current.copy(0.2f))
        .fillMaxWidth()

    Column(Modifier.padding(5.dp)) { //TODO fix the maximum height of each area
        Box(mod.preferredHeightIn(min = 80.dp, max = 100.dp)) {
            messageArea.orDefault { Box {} }()
        }
        Box(mod.wrapContentHeight()) {
            inputArea.orDefault { Box {} }()
        }
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

/**
 * A utility extension function to provide a default value if a null is received.
 * @param default the value to use in case of nulls
 */
internal fun <T> T?.orDefault(default: T): T = this ?: default