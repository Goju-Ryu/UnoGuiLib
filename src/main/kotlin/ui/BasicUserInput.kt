package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * This composable creates an arbitrary number of buttons.
 * The buttens will have a standard behavior that should depend only on the supplied name
 */
@Composable
internal fun Buttons(vararg buttonNames: String, buttonOnClick: ButtonOnClick = {}) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (name in buttonNames) {
            Button(onClick = { buttonOnClick(name) }, Modifier.padding(5.dp)) {
                Text(name)
            }
        }
    }
}

/**
 * A special onClickListener for buttons that takes the name of a button as an argument
 */
internal typealias ButtonOnClick = (name: String) -> Unit