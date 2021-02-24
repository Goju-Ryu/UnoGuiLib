package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Player


/**
 * This screen is meant for setting up the game.
 * In this screen it is easy to ask for number of players, names and more.
 */
@Composable
internal fun SetUpScreen(
    messageArea: (@Composable () -> Unit)? = null,
    inputArea: (@Composable () -> Unit)? = null,
    players: SnapshotStateList<Player>
) {
    val mod = Modifier
        .border(2.dp, LocalContentColor.current)
        .fillMaxWidth()

    Column(Modifier.padding(5.dp)) { //TODO fix the maximum height of each area
        Box(mod.height(80.dp)) {
            messageArea.orDefault { Box {} }()
        }
        Box(mod.wrapContentHeight()) {
            inputArea.orDefault { Box {} }()
        }

        Row {
            for (player in players) {
                PlayerImageCard(player)
            }
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

internal fun <T:  ( @Composable () -> Unit)?> T?.orDefault(default: T): T = this ?: default