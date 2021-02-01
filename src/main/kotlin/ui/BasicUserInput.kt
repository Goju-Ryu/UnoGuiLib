package ui

import androidx.compose.foundation.Interaction
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * This composable creates an arbitrary number of buttons.
 * The buttens will have a standard behavior that should depend only on the supplied name
 */
@Composable
internal fun Buttons(onInputConfirmed: OnInputConfirmed, vararg buttonNames: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (name in buttonNames) {
            Button(onClick = { onInputConfirmed(name) }, Modifier.padding(5.dp)) {
                Text(name)
            }
        }
    }
}

@Composable
internal fun EditText(onInputConfirmed: OnInputConfirmed, buttonText: String = "Ok", singleLine: Boolean = true) {
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
        Button({ onInputConfirmed(text.value) }, Modifier.wrapContentSize()) {
            Text(buttonText)
        }
    }
}

@Composable
internal fun DropDownMenu(onInputConfirmed: OnInputConfirmed, vararg options: String) {
    val expanded = remember {  mutableStateOf(true) }
    DropdownMenu(
        toggle =  { Button({expanded.value = !expanded.value}){ Text("Menu")} },
        expanded = expanded.value,
        onDismissRequest = {},
    ) {
        for (option in options) {
            DropdownMenuItem(onClick = {onInputConfirmed(option)}) {
                Text(option)
            }
        }
    }
}

/**
 * A special onClickListener for buttons that takes the name of a button as an argument
 */
internal typealias OnInputConfirmed = (name: String) -> Unit