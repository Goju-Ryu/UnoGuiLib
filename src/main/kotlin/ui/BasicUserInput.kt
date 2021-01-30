package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@Composable
internal fun EditText(buttonText: String = "Ok", singleLine: Boolean = true, onAccept: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val text = remember { mutableStateOf("") }
        TextField(
            text.value,
            onValueChange = { text.value = it },
            singleLine = singleLine,
            modifier = Modifier
                .padding(5.dp)
                .preferredWidth(400.dp)
        )
        Button({ onAccept(text.value) }, Modifier.wrapContentSize()) {
            Text(buttonText)
        }
    }
}

/**
 * A special onClickListener for buttons that takes the name of a button as an argument
 */
internal typealias ButtonOnClick = (name: String) -> Unit